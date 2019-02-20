package com.cherry.framework.core.model;

import java.io.Serializable;
import java.util.List;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/20 22:56
 */
public class Page<T> implements Serializable {

    private Long total = 0L;
    private List<T> rows;
    private Object extra = null;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }
}
