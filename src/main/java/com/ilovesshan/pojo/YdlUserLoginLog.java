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
    private Long loginId;
    private Long userId;
    private String token;
    private String username;
    private String loginIp;
    private String loginLocaltion;
    private String browser;
    private String systemOs;
    private Date loginTime;
    private YdlUser ydlUser;

}

