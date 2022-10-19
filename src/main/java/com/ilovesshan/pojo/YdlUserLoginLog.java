package com.ilovesshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户登录表(YdlUserLoginLog)实体类
 *
 * @author makejava
 * @since 2022-10-19 09:20:53
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class YdlUserLoginLog implements Serializable {
    private static final long serialVersionUID = 524087621165237734L;
    /**
     * 用户ID
     */
    private Long loginId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户账号
     */
    private String token;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 登录IP
     */
    private String loginIp;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 用户邮箱
     */
    private String loginLocaltion;
    /**
     * 浏览器类型
     */
    private String browser;
    /**
     * 操作系统
     */
    private String systemOs;

    /**
     * 用户信息
     */
    private YdlUser ydlUser;

}

