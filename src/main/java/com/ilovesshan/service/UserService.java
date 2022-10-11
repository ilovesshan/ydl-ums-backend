package com.ilovesshan.service;

import com.ilovesshan.pojo.YdlUser;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/11
 * @description:
 */

public interface UserService {
    YdlUser selectUserByName(String username);
}
