package com.ilovesshan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ilovesshan.common.R;
import com.ilovesshan.pojo.YdlUser;
import com.ilovesshan.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/10
 * @description:
 */

@Slf4j
@Controller
public class AuthController {


    @Resource
    private AuthService authService;

    @PostMapping("/login")
    @ResponseBody
    public R login(@RequestBody YdlUser ydlUser) {
        String password = ydlUser.getPassword();
        String userName = ydlUser.getUsername();
        if (password == null || userName == null) {
            return R.fail("用户名或密码不能为空", null);
        }
        try {
            return authService.login(userName, password);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return R.error(e.getMessage(), null);
        }
    }


    @PostMapping("/register")
    @ResponseBody
    public R logout(@RequestBody YdlUser ydlUser) {
        String password = ydlUser.getPassword();
        String userName = ydlUser.getUsername();
        if (password == null || userName == null) {
            return R.fail("用户名或密码不能为空", null);
        }
        return authService.register(userName, password);
    }


    @PostMapping("/logout")
    @ResponseBody
    public R logout() {
        return authService.logout();
    }
}
