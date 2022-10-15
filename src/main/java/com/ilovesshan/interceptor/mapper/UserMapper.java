package com.ilovesshan.interceptor.mapper;

import com.ilovesshan.pojo.YdlUser;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/11
 * @description:
 */

public interface UserMapper {
    YdlUser selectUserByName(String username);
}
