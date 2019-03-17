package com.cherry.framework.core.rowmapper;

import com.cherry.framework.core.model.ClassMapping;
import com.cherry.framework.core.model.DBMappingProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.*;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/20 23:04
 */
public class ADBeanPropertyRowMapper<T> implements RowMapper<T> {

    private static final Logger lg = LoggerFactory.getLogger(ADBeanPropertyRowMapper.class);
    private static final Map<String, ClassMapping> mappingClassCache = new HashMap<>();
    private static final Map<String, Map<String, PropertyDescriptor>> mappingClassNormalPropertyCache = new HashMap<>();
    private static final ReentrantLock lock = new ReentrantLock();
    private Class<T> mappedClass;
    private Map<Integer, String> rsmdFieldCache = new HashMap<>();
    private boolean checkFullyPopulated = false;
    private boolean primitivesDefaultedForNullValue = false;

    public ADBeanPropertyRowMapper(Class<T> clazz) {
        this.mappedClass = clazz;
        initialize(clazz);
    }

    private static void initialize(Class<?> clazz) {
        if (!mappingClassCache.containsKey(clazz.getName())) {
            lock.lock();
            try {
                if (!mappingClassCache.containsKey(clazz.getName())) {
                    ClassMapping mapping = new ClassMapping();
                    String atName = clazz.getSimpleName();
                    Annotation at;
                    if (clazz.getAnnotations().length > 0) {
                        at = clazz.getAnnotations()[0];
                        if (at instanceof DBTable) {
                            atName = ((DBTable) at).name();
                        }
                    }
                    mapping.setTableName(atName);
                    lg.info("");
                    lg.info(">>> {} --> {} <<<", clazz.getName(), mapping.getTableName());
                    PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(clazz);
                    PropertyDescriptor[] temp = propertyDescriptors;
                    int propertyDescriptorsLength = propertyDescriptors.length;
                    for (int i = 0; i < propertyDescriptorsLength; ++i) {
                        PropertyDescriptor pd = temp[i];
                        if (null != pd.getWriteMethod() && null != pd.getReadMethod()) {
                            atName = pd.getName();
                            try {
                                Field field = clazz.getDeclaredField(pd.getName());
                                if (null != field.getAnnotations() && field.getAnnotations().length > 0) {
                                    at = field.getAnnotations()[0];
                                    if (at instanceof DBColumn) {
                                        DBColumn column = (DBColumn) at;
                                        if (column.isPrimaryKey()) {
                                            mapping.setPkProperty(pd.getName());
                                            mapping.setPkProperty(atName.toUpperCase());
                                        } else {
                                            if (StringUtils.isEmpty(column.name()) || "Null".equalsIgnoreCase(column.name())) {
                                                lg.debug("普通字段属性：{}", pd.getName());
                                                if (!mappingClassNormalPropertyCache.containsKey(clazz.getName())) {
                                                    mappingClassNormalPropertyCache.put(clazz.getName(), new HashMap<>());
                                                }
                                                mappingClassNormalPropertyCache.get(clazz.getName()).put(pd.getName().toUpperCase(), pd);
                                                continue;
                                            }
                                            atName = column.name();

                                        }
                                    }
                                }
                            } catch (Exception e) {
                                lg.error("{}的o/r mapping annotation信息获取失败：{}", pd.getName(), e.getMessage());
                            }
                            lg.info("{} ---> {}", pd.getName(), StringUtils.capitalize(atName));
                            DBMappingProperty mp = new DBMappingProperty();
                            mp.setName(pd.getName());
                            mp.setType(pd.getPropertyType());
                            mapping.addProperty(atName.toUpperCase(), mp);
                        }
                    }
                    lg.info("Primary Key：{}", mapping.getPkColumn());
                    mappingClassCache.put(clazz.getName(), mapping);
                    return;
                }
            } finally {
                lock.unlock();
            }
        }
    }

    public static ClassMapping getClassMapping(Class<?> clazz) {
        initialize(clazz);
        return mappingClassCache.get(clazz.getName());
    }


    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        T rowObject = BeanUtils.instantiateClass(this.mappedClass);
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(rowObject);
        ClassMapping mappingRole = mappingClassCache.get(this.mappedClass.getName());
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();
        Map<String, PropertyDescriptor> normalFieldCache = mappingClassNormalPropertyCache.get(this.mappedClass.getName());

        for (int index = 1; index < columnCount; ++index) {
            String columnName;
            if (this.rsmdFieldCache.containsKey(index)) {
                columnName = this.rsmdFieldCache.get(index);
            } else {
                columnName = JdbcUtils.lookupColumnName(rsmd, index);
                this.rsmdFieldCache.put(index, columnName);
            }

            String propertyName;
            Class propertyClass;
            if (mappingRole.hasProperty(columnName)) {
                DBMappingProperty dbMP = mappingRole.getProperty(columnName);
                propertyName = dbMP.getName();
                propertyClass = dbMP.getType();
            } else {
                if (null == normalFieldCache || !normalFieldCache.containsKey(columnName)) {
                    continue;
                }
                PropertyDescriptor pd = normalFieldCache.get(columnName);
                propertyName = pd.getName();
                propertyClass = pd.getPropertyType();
            }
            try {
                Object value = this.getColumnValue(rs, index, propertyClass);
                bw.setPropertyValue(columnName, value);
            } catch (NotWritablePropertyException e) {
                throw new DataRetrievalFailureException("Unable map to column " + columnName + " to property " + propertyName, e);
            }
        }

        return rowObject;
    }

    private Object getColumnValue(ResultSet rs, int index, Class<?> propertyType) throws SQLException {
        return JdbcUtils.getResultSetValue(rs, index, propertyType);
    }

    public boolean isCheckFullyPopulated() {
        return checkFullyPopulated;
    }

    public void setCheckFullyPopulated(boolean checkFullyPopulated) {
        this.checkFullyPopulated = checkFullyPopulated;
    }

    public boolean isPrimitivesDefaultedForNullValue() {
        return primitivesDefaultedForNullValue;
    }

    public void setPrimitivesDefaultedForNullValue(boolean primitivesDefaultedForNullValue) {
        this.primitivesDefaultedForNullValue = primitivesDefaultedForNullValue;
    }
}
