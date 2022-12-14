package com.ilovesshan.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ilovesshan.common.R;
import com.ilovesshan.common.RedisTemplate;
import com.ilovesshan.constant.YdlConstants;
import com.ilovesshan.handler.CustomObjectMapper;
import com.ilovesshan.pojo.YdlUserLoginLog;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/11
 * @description:
 */

public class AuthHandlerInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CustomObjectMapper objectMapper;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        R responseExNoToken = R.fail("暂无访问/操作权限", null);
        R responseExNoAuth = R.fail("该账号可能处于异地登录状态，请重新登录再试", null);
        response.setContentType("application/json;charset=utf-8");
        // 添加白名单
        List<String> whiteNames = Arrays.asList("/ums/login", "/ums/logout", "/ums/attachment/upload", "/ums/attachment/download", "/ums/register");

        boolean canThrough = false;
        for (String name : whiteNames) {
            if (request.getRequestURI().contains(name)) {
                canThrough = true;
                break;
            }
        }

        if (!canThrough) {
            String authorization = request.getHeader("Authorization");
            String username = request.getHeader("username");
            // token 不存在
            if (authorization == null || "".equals(authorization)) {
                response.setStatus(301);
                response.getWriter().print(objectMapper.writeValueAsString(responseExNoToken));
                return false;
            }

            // 根据token去redis中查
            String key = YdlConstants.TOKEN_PREFIX + username + ":" + authorization;
            YdlUserLoginLog user = redisTemplate.getObject(key, new TypeReference<YdlUserLoginLog>() {});
            if (user == null) {
                response.setStatus(301);
                response.getWriter().print(objectMapper.writeValueAsString(responseExNoAuth));
                return false;
            }

            // 给token延时(重新设置过期时间)
            redisTemplate.expire(key, YdlConstants.TOKEN_EXPIRE);
        }
        return true;
    }
}
