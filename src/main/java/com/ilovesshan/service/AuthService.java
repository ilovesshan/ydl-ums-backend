package com.ilovesshan.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ilovesshan.common.R;
import com.ilovesshan.pojo.YdlUpdatePassword;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/11
 * @description:
 */

public interface AuthService {
    R login(String username, String password) throws JsonProcessingException;

    R register(String username, String password);

    R logout();

    R permission(Long userId);

    R updatePassword(YdlUpdatePassword updatePassword);
}
