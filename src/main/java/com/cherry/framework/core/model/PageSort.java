package com.cherry.framework.core.model;

import java.io.Serializable;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/20 22:53
 */
public class PageSort implements Serializable {

    private Long pageNumber = 1L;
    private Integer pageSize = 20;
    private String sortName;
    private String sortOrder;

    public Long getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Long pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
}
