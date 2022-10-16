package com.ilovesshan.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ilovesshan.handler.CustomObjectMapper;
import com.ilovesshan.common.R;
import com.ilovesshan.common.RedisTemplate;
import com.ilovesshan.constant.YdlConstants;
import com.ilovesshan.mapper.AuthMapper;
import com.ilovesshan.pojo.YdlAuth;
import com.ilovesshan.pojo.YdlRole;
import com.ilovesshan.pojo.YdlUser;
import com.ilovesshan.pojo.YdlUserLogin;
import com.ilovesshan.service.AuthService;
import com.ilovesshan.service.YdlUserService;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/11
 * @description:
 */

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Resource
    private YdlUserService userService;

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
            map = objectMapper.readValue(body, new TypeReference<Map<String, String>>() {
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        String location = map.get("addr") + map.get("pro") + map.get("city") + map.get("region");

        // 生成token 组装用户登录信息
        String token = UUID.randomUUID().toString().replace("-", "");
        YdlUserLogin userLogin = YdlUserLogin.builder().token(token).username(username).loginId(ydlUser.getUserId()).loginTime(new Date()).userId(ydlUser.getUserId()).systemOs(userAgent.getOperatingSystem().toString()).browser(userAgent.getBrowser().toString()).loginIp(request.getRemoteAddr()).loginLocaltion(location).build();
        // 将用户登录日志存在数据库中
        authMapper.insert(userLogin);


        // 查看 redis中是否存在该用户记录的信息
        Set<String> keys = redisTemplate.keys(YdlConstants.TOKEN_PREFIX + username + "*");

        // 存在，则不能登录(避免多人同时在线)
        // if (!keys.isEmpty()) {
        //     return R.fail("该账号在其他地方登录，请稍后再试", null);
        // }
        // 不存在, 可以登录,将用户信息存在 redis中
        // redisTemplate.setObject(YdlConstants.TOKEN_PREFIX + username + ":" + token, userLogin, YdlConstants.TOKEN_EXPIRE);

        // 存在就删除掉
        keys.forEach(key -> redisTemplate.remove(key));

        // 将新的用户信息存在redis中
        redisTemplate.setObject(YdlConstants.TOKEN_PREFIX + username + ":" + token, userLogin, YdlConstants.TOKEN_EXPIRE);

        // 将登录信息和用户信息返回给用户
        userLogin.setYdlUser(ydlUser);
        return R.success("登录成功", userLogin);
    }

    @Override
    public R register(String username, String password) {
        return null;
    }


    @Override
    public R logout() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String authorization = request.getHeader("Authorization");
        String username = request.getHeader("username");
        redisTemplate.remove(YdlConstants.TOKEN_PREFIX + username + ":" + authorization);
        return R.success("退出成功", null);
    }

    @Override
    public R permission(Long userId) {
        YdlAuth rolesAndPermissions = authMapper.permission(userId);

        // 将角色信息和权限信息放到redis中
        // 角色信息:    role:token = [admin, xxx, xxx]
        // 权限信息:    permission:token = [/system-management/menu-management, /home, xxx]


        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String authorization = request.getHeader("Authorization");

        String roleKey = YdlConstants.ROLE_PREFIX + authorization;
        String permissionKey = YdlConstants.PERMISSION_PREFIX + authorization;

        // 角色列表
        List<String> roles = rolesAndPermissions.getYdlRoles().stream().map(YdlRole::getRoleName).collect(Collectors.toList());

        List<String> permissions = new ArrayList<>();
        // 权限列表
        rolesAndPermissions.getYdlRoles().forEach(role -> role.getYdlMenus().forEach(menu -> permissions.add(menu.getPath())));

        redisTemplate.setObject(roleKey, roles, YdlConstants.TOKEN_EXPIRE);
        redisTemplate.setObject(permissionKey, permissions, YdlConstants.TOKEN_EXPIRE);


        HashMap<String, Object> data = new HashMap<>();

        data.put("roles", roles);
        data.put("permissions", permissions);
        data.put("rolesAndPermissions", rolesAndPermissions);

        return R.success("获取成功", data);
    }
}
