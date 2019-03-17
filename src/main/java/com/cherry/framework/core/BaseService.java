package com.cherry.framework.core;

import com.cherry.framework.core.model.ClassMapping;
import com.cherry.framework.core.model.DBMappingProperty;
import com.cherry.framework.core.model.Page;
import com.cherry.framework.core.model.PageSort;
import com.cherry.framework.core.rowmapper.ADBeanPropertyRowMapper;
import oracle.sql.TIMESTAMP;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.*;

/**
 * BaseService
 *
 * @author Leon
 * @version 2019/3/15 23:39
 */
@Service
public class BaseService {

    private static final Logger lg = LoggerFactory.getLogger(BaseService.class);

    public BaseService() {}

    @Autowired
    public JdbcTemplate jdbc;

    private List<?>[] getClassMappingFieldAndValueList(Object obj, String[] includeFields, String[] excludeFields, ClassMapping mapping) {
        List<?>[] result = new ArrayList[2];
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(obj);
        List<String> fields = new ArrayList<>();
        List<Object> fieldValues = new ArrayList<>();
        Map<String, DBMappingProperty> columns = mapping.getAllProperties();
        Iterator<String> it = columns.keySet().iterator();
        for (;;) {
            DBMappingProperty p;
            String columnName;
            do {
                label:
                do {
                    while (it.hasNext()) {
                        columnName = it.next();
                        p = columns.get(columnName);
                        if (!mapping.getPkColumn().equals(columnName) || null != bw.getPropertyValue(p.getName()) && -1L != (Long)bw.getPropertyValue(p.getName())) {
                            continue label;
                        }
                    }
                    result[0] = fields;
                    result[1] = fieldValues;
                    return result;
                } while (null != includeFields && !ArrayUtils.contains(includeFields, p.getName()));
            } while (null != excludeFields && ArrayUtils.contains(excludeFields, p.getName()));

            fields.add(columnName);
            fieldValues.add(bw.getPropertyValue(p.getName()));
        }
    }

    public Long insertIntoTable(Object obj) throws Exception {
        return this.insertIntoTable(obj, null, null);
    }

    public Long insertIntoTable(Object obj, String[] includeFields) throws Exception {
        return this.insertIntoTable(obj, includeFields, null);
    }

    public Long insertIntoTable(Object obj, String[] includeFields, String[] excludeFields) throws Exception {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbc.update(con -> {
            ClassMapping mapping = ADBeanPropertyRowMapper.getClassMapping(obj.getClass());
            List<?>[] fv = this.getClassMappingFieldAndValueList(obj, includeFields, excludeFields, mapping);
            List<String> fields = (List<String>) fv[0];
            List<Object> fieldValues = (List<Object>) fv[1];
            String sql = String.format("INSERT INTO %s (<fields>) VALUES(<values>)", mapping.getTableName());
            sql = sql.replace("<fields>", StringUtils.join(fields, ", "));
            sql = sql.replace("<values>", StringUtils.repeat("?, ", fieldValues.size() - 1) + "?");
            lg.debug("Insert SQL：{}", sql);
            PreparedStatement ps = con.prepareStatement(sql, new String[]{mapping.getPkColumn()});
            Object param = null;
            for(int i = 0; i < fieldValues.size(); ++i) {
                param = fieldValues.get(i);
                if ("Oracle".equalsIgnoreCase(CHERRY.DB_TYPE) && param instanceof Date) {
                    ps.setObject(i + 1, new TIMESTAMP(new Timestamp(((Date)param).getTime())));
                } else {
                    ps.setObject(i + 1, param);
                }
            }
            return ps;
        }, keyHolder);
        return keyHolder.getKey().longValue();
    }

    public void updateTable(Object obj, String[] includeFields, String[] excludeFields) {
        ClassMapping mapping = ADBeanPropertyRowMapper.getClassMapping(obj.getClass());
        List<?>[] fv = this.getClassMappingFieldAndValueList(obj, includeFields, excludeFields, mapping);
        List<String> fields = (List<String>) fv[0];

        for(int i = 0; i < fields.size(); ++i) {
            String column = fields.get(i);
            fields.set(i, column + "=?");
        }

        List<Object> fieldValues = (List<Object>) fv[1];
        BeanWrapper bw = PropertyAccessorFactory.forBeanPropertyAccess(obj);
        fieldValues.add(bw.getPropertyValue(mapping.getPkProperty()));
        String sql = "UPDATE " + mapping.getTableName() + " SET " + StringUtils.join(fields, ", ") + " WHERE " + mapping.getPkColumn() + "=?";
        lg.debug("Update SQL：{}", sql);
        this.jdbc.update(sql, fieldValues.toArray());
    }

    public void updateTable(Object obj, String[] includeFields) throws Exception {
        this.updateTable(obj, includeFields, null);
    }

    protected boolean isFieldDuplicate(String table, String fieldName, Object value, String where) throws Exception {
        String sql = "SELECT ID FROM " + table + " WHERE " + fieldName + "=?";
        if (StringUtils.isNotBlank(where)) {
            sql = sql + " AND " + where;
        }

        SqlRowSet rs = this.jdbc.queryForRowSet(sql, new Object[]{value});
        return rs.next();
    }

    protected boolean isUsedByOtherTable(String table, String fieldName, Object value, String where) {
        String sql = "SELECT COUNT(*) AS Num FROM " + table + " WHERE " + fieldName + "=?" + (StringUtils.isNotBlank(where) ? " AND " + where : "");
        SqlRowSet rs = this.jdbc.queryForRowSet(sql, new Object[]{value});
        rs.next();
        return 0 != rs.getInt("Num");
    }

    protected <T> List<T> findByList(String sql, Object[] params, Class<T> voClazz) throws Exception {
        return this.jdbc.query(sql, params, new ADBeanPropertyRowMapper(voClazz));
    }

    protected <T> List<T>  findByList(String sql, Class<T> voClazz, Object... params) throws Exception {
        return this.findByList(sql, params, voClazz);
    }

    protected <T> T load(Class<T> voClazz, Long id) throws Exception {
        ClassMapping mapping = ADBeanPropertyRowMapper.getClassMapping(voClazz);
        List<T> oneRow = this.findByList("SELECT * FROM " + mapping.getTableName() + " WHERE " + mapping.getPkColumn() + "=?", voClazz, id);
        return null != oneRow && 1 == oneRow.size() ? oneRow.get(0) : null;
    }

    protected <T> List<T> findByTopN(String sql, Integer topN, Class<T> voClazz, Object... params) throws Exception {
        if ("MySQL".equalsIgnoreCase(CHERRY.DB_TYPE)) {
            sql = sql + " LIMIT 0," + topN;
        } else if ("Oracle".equalsIgnoreCase(CHERRY.DB_TYPE)) {
            sql = "SELECT RowNum, Cherry.* FROM (" + sql + ") Cherry WHERE RowNum<=" + topN;
        }

        lg.debug("Top N查询SQL：{}", sql);
        return this.jdbc.query(sql, params, new ADBeanPropertyRowMapper(voClazz));
    }

    protected <T> Page<T> findByPage(String sql, Object[] params, Class<T> voClazz, PageSort ps) throws Exception {
        Page<T> page = new Page<>();
        String temp = "SELECT COUNT(*) " + sql.substring(sql.toLowerCase().indexOf("from"));
        SqlRowSet rs = this.jdbc.queryForRowSet(temp, params);
        if (rs.next()) {
            page.setTotal(rs.getLong(1));
        }
        if (null != ps) {
            if ((ps.getPageNumber() - 1L) * (long)ps.getPageSize() >= page.getTotal()) {
                ps.setPageNumber(ps.getPageNumber() - 1L);
            }

            if (ps.getPageNumber() < 1L) {
                ps.setPageNumber(1L);
            }

            lg.debug("分页参数：pageNumber-{}，pageSize-{}，sortName-{}，sortOrder-{}", new Object[]{ps.getPageNumber(), ps.getPageSize(), ps.getSortName(), ps.getSortOrder()});
        }
        String sortField = "";
        if (null != ps && StringUtils.isNotBlank(ps.getSortName())) {
            sortField = ps.getSortName();
            Map<String, DBMappingProperty> columns = ADBeanPropertyRowMapper.getClassMapping(voClazz).getAllProperties();
            Iterator it = columns.keySet().iterator();

            while(it.hasNext()) {
                String s = (String)it.next();
                DBMappingProperty pp = columns.get(s);
                if (sortField.equals(pp.getName())) {
                    sortField = s;
                    lg.debug("数据库排序字段：{}->{}", ps.getSortName(), s);
                    break;
                }
            }
        }

        if (StringUtils.isNotBlank(sortField)) {
            assert ps != null;
            sql = sql + " ORDER BY " + sortField + " " + ps.getSortOrder();
        }

        if ("MySQL".equalsIgnoreCase(CHERRY.DB_TYPE)) {
            if (null != ps && -1 != ps.getPageSize()) {
                sql = sql + " LIMIT " + (ps.getPageNumber() - 1L) * (long)ps.getPageSize() + "," + ps.getPageSize();
            }
        } else if ("Oracle".equalsIgnoreCase(CHERRY.DB_TYPE) && null != ps && -1 != ps.getPageSize()) {
            sql = "SELECT * FROM (SELECT Cherry.*, RowNum ForpRowNum FROM (" + sql + ") Cherry) WHERE ForpRowNum>" + (ps.getPageNumber() - 1L) * (long)ps.getPageSize() + " AND ForpRowNum<=" + ps.getPageNumber() * (long)ps.getPageSize();
        }

        lg.debug("分页查询SQL：{}", sql);
        List<T> rows = this.jdbc.query(sql, params, new ADBeanPropertyRowMapper(voClazz));
        page.setRows(rows);
        return page;
    }

    protected <T> Page<T> findByPage(String sql, Class<T> voClazz, PageSort ps, Object... params) throws Exception {
        return this.findByPage(sql, params, voClazz, ps);
    }





}
