package com.ilovesshan.aspect;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ilovesshan.anotation.HasPermission;
import com.ilovesshan.anotation.HasRole;
import com.ilovesshan.common.RedisTemplate;
import com.ilovesshan.constant.YdlConstants;
import com.ilovesshan.exception.AuthException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/15
 * @description:
 */


@Component
@Aspect
@Slf4j
public class AuthAspect {

    @Resource
    private RedisTemplate redisTemplate;

    @Before(value = "@annotation(hasPermission)")
    public void beforeRoleAdvice(JoinPoint joinPoint, HasPermission hasPermission) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取token
        String authorization = request.getHeader("Authorization");
        String permissionKey = YdlConstants.PERMISSION_PREFIX + authorization;

        // 需要的权限
        String[] needPermissions = hasPermission.value();

        // 获取实际的权限
        List<String> permissions = redisTemplate.getObject(permissionKey, new TypeReference<List<String>>() {
        });

        boolean verifyResult = false;
        for (String permission : needPermissions) {
            if (permissions.contains(permission)) {
                verifyResult = true;
                break;
            }
        }

        // 无权限
        if (!verifyResult) {
            throw new AuthException("抱歉，您暂无该项操作权限");
        }
    }


    @Before("@annotation(hasRole)")
    public void beforePermissionAdvice2(JoinPoint joinPoint, HasRole hasRole) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        // 获取token
        String authorization = request.getHeader("Authorization");
        String roleKey = YdlConstants.ROLE_PREFIX + authorization;

        // 需要的权限
        String[] needoles = hasRole.value();

        // 获取实际的权限
        List<String> roles = redisTemplate.getObject(roleKey, new TypeReference<List<String>>() {
        });

        boolean verifyResult = false;
        for (String role : needoles) {
            if (roles.contains(role)) {
                verifyResult = true;
                break;
            }
        }

        // 无权限
        if (!verifyResult) {
            throw new AuthException("抱歉，您暂无该项操作权限");
        }
    }
}
