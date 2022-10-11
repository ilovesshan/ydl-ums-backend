package com.ilovesshan.controller;

import com.ilovesshan.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/10
 * @description:
 */

@Slf4j
@RestController()
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

}
