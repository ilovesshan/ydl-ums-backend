package com.ilovesshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 操作日志(YdlOperLog)实体类
 *
 * @author makejava
 * @since 2022-10-16 16:21:32
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YdlOperLog implements Serializable {
    private static final long serialVersionUID = -63793751847847826L;
    /**
     * 日志主键
     */
    private Integer operId;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 业务模块
     */
    private String businessModule;
    /**
     * 具体描述
     */
    private String businessDescribe;
    /**
     * api方法
     */
    private String method;
    /**
     * 请求方式
     */
    private String requestMethod;
    /**
     * 操作人员
     */
    private String operName;
    /**
     * 请求url
     */
    private String operUrl;
    /**
     * 操作地址
     */
    private String operIp;
    /**
     * 操作状态
     */
    private Integer status;
    /**
     * 错误消息
     */
    private String errormsg;
    /**
     * 操作时间
     */
    private Date opertime;

}

