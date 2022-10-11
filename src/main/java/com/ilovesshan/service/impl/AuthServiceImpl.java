package com.ilovesshan.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ilovesshan.common.CustomObjectMapper;
import com.ilovesshan.common.R;
import com.ilovesshan.common.RedisTemplate;
import com.ilovesshan.constant.YdlConstants;
import com.ilovesshan.mapper.AuthMapper;
import com.ilovesshan.pojo.YdlUser;
import com.ilovesshan.pojo.YdlUserLogin;
import com.ilovesshan.service.AuthService;
import com.ilovesshan.service.UserService;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/11
 * @description:
 */

@Service
public class AuthServiceImpl implements AuthService {

    @Resource
    private UserService userService;

    @Resource
    private AuthMapper authMapper;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private CustomObjectMapper objectMapper;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public R login(String username, String password) {

        // 查询用户存不存在
        YdlUser ydlUser = userService.selectUserByName(username);
        if (ydlUser == null) {
            return R.fail("该用户不存在", null);
        }

        // 存在则比较密码是否项相等
        if (!password.equals(ydlUser.getPassword())) {
            return R.fail("密码错误", null);
        }

        // 获取客户端一些源信息
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        UserAgent userAgent = new UserAgent(request.getHeader("User-Agent"));

        // 通过ip获取其所属的地址
        ResponseEntity<String> result = restTemplate.getForEntity("https://whois.pconline.com.cn/ipJson.jsp?ip=" + request.getRemoteHost() + "&json=true", String.class);
        String body = result.getBody();
        Map<String, String> map = null;
        try {
            map = objectMapper.readValue(body, new TypeReference<Map<String, String>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String location = map.get("addr") + map.get("pro") + map.get("city") + map.get("region");

        // 生成token 组装用户登录信息
        String token = UUID.randomUUID().toString().replace("-", "");
        YdlUserLogin userLogin = YdlUserLogin.builder()
                .token(token)
                .username(username)
                .loginId(ydlUser.getUserId())
                .loginTime(new Date())
                .userId(ydlUser.getUserId())
                .systemOs(userAgent.getOperatingSystem().toString())
                .browser(userAgent.getBrowser().toString())
                .loginIp(request.getRemoteAddr())
                .loginLocaltion(location)
                .build();
        // 将用户登录日志存在数据库中
        authMapper.insert(userLogin);

        // 将用户信息存在 redis中
        redisTemplate.setObject(YdlConstants.TOKEN_PREFIX + token, userLogin, YdlConstants.TOKEN_EXPIRE);

        // 将登录信息和用户信息返回给用户
        userLogin.setYdlUser(ydlUser);
        return R.success("登录成功", userLogin);
    }

    @Override
    public R register(String username, String password) {
        return null;
    }
}
