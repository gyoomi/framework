package com.gyoomi.adam.core.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/2/20 22:53
 */
@Data
public class PageSort implements Serializable {

    private Long pageNo = 1L;

    private Integer pageSize = 20;

    private String sortName;

    private String sortOrder;

}
