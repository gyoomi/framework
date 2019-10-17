package com.gyoomi.adam.rbac.model;

import com.gyoomi.adam.rbac.enums.LogBusinessType;
import com.gyoomi.adam.rbac.enums.LogOperatorType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 类功能描述
 *
 * @author Leon
 * @version 2019/4/7 14:58
 */
@Accessors(chain = true)
@Data
public class Log implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 操作模块
     */
    private String title;

    /**
     * 业务类型：
     *     0=其它,1=新增,2=修改,3=删除,4=授权,5=导出,6=导入,7=强退,8=生成代码,9=清空数据
     * @see LogBusinessType
     */
    private Integer businessType;

    /**
     * 请求方法
     */
    private String method;

    /**
     * 操作类别 : 0=其它,1=后台用户,2=手机端用户
     * @see LogOperatorType
     */
    private Integer operatorType;

    /**
     * 操作人员
     */
    private String operatorName;

    /**
     * 部门名称
     */
    private String deptName;

    /**
     * 请求url
     */
    private String operatorUrl;

    /**
     * 操作地址
     */
    private String operatorIp;

    /**
     * 操作地点
     */
    private String operatorLocation;

    /**
     * 请求参数
     */
    private String operatorParam;

    /**
     * 操作状态（0正常 1异常）
     */
    private Integer status;

    /**
     * 错误消息
     */
    private String errorMsg;

    /**
     * 操作时间
     */
    private Date operatorDate;
}
