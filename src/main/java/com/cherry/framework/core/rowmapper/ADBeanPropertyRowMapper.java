package com.cherry.framework.core.rowmapper;

import com.cherry.framework.core.model.ClassMapping;
import com.fasterxml.jackson.databind.util.BeanUtil;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.RowMapper;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.sql.ResultSet;
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


                }
            } finally {
                lock.unlock();
            }
        }
    }


    @Override
    public T mapRow(ResultSet rs, int rowNum) throws SQLException {
        return null;
    }
}
