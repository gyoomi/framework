package com.cherry.framework.core.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/20 22:49
 */
public class ClassMapping implements Serializable {

    private String tableName;
    private String pkColumn = "ID";
    private String pkProperty = "id";
    private Map<String, DBMappingProperty> fields = new HashMap<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPkColumn() {
        return pkColumn;
    }

    public void setPkColumn(String pkColumn) {
        this.pkColumn = pkColumn;
    }

    public String getPkProperty() {
        return pkProperty;
    }

    public void setPkProperty(String pkProperty) {
        this.pkProperty = pkProperty;
    }

    public void addProperty(String columnName, DBMappingProperty mp) {
        this.fields.put(columnName, mp);
    }

    public boolean hasProperty(String columnName) {
        return this.fields.containsKey(columnName);
    }

    public DBMappingProperty getProperty(String columnName) {
        return this.fields.get(columnName);
    }

    public Map<String, DBMappingProperty> getAllProperties() {
        return this.fields;
    }
}
