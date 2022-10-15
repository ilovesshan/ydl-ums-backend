package com.ilovesshan.service.impl;

import com.ilovesshan.interceptor.mapper.UserMapper;
import com.ilovesshan.pojo.YdlUser;
import com.ilovesshan.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/11
 * @description:
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public YdlUser selectUserByName(String username) {
        return userMapper.selectUserByName(username);
    }
}
