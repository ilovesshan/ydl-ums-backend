package com.ilovesshan.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/17
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class YdlUpdatePassword implements Serializable {
    private static final long serialVersionUID = 4282750457352987001L;
    private long userId;
    private String oldPassword;
    private String newPassword;
    private String sureNewPassword;
}
