package com.ilovesshan.mapper;

import com.ilovesshan.pojo.YdlAuth;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/11
 * @description:
 */

public interface AuthMapper {
    YdlAuth permission(Long userId);
}
