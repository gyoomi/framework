package com.gyoomi.adam.core.model;

import lombok.Builder;
import lombok.Data;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/10/19 19:38
 */
@Data
@Builder
public class Response {

    /**
     * 状态码：正常 - 200；错误 - 其他
     */
    private Integer code;

    /**
     * 提示消息
     */
    private String msg;

    /**
     * 业务数据
     */
    private Object data;

}
