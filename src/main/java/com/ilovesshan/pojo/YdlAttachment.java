package com.ilovesshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 系统附件表(YdlAttachment)实体类
 *
 * @author makejava
 * @since 2022-10-18 19:29:25
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YdlAttachment implements Serializable {
    private static final long serialVersionUID = -70918065842542014L;
    /**
     * 主键
     */
    private Integer id;
    /**
     * 访问地址
     */
    private String url;
    /**
     * 创建人
     */
    private String createBy;
    /**
     * 创建人id
     */
    private Integer createByUserId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}

