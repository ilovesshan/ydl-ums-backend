package com.ilovesshan.interceptor;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ilovesshan.common.R;
import com.ilovesshan.common.RedisTemplate;
import com.ilovesshan.constant.YdlConstants;
import com.ilovesshan.handler.CustomObjectMapper;
import com.ilovesshan.pojo.YdlUserLogin;
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
        R responseEx = R.fail("暂无访问/操作权限", null);
        // 添加白名单
        List<String> list = Arrays.asList("/ums/login", "/ums/register");
        if (!list.contains(request.getRequestURI())) {
            String authorization = request.getHeader("Authorization");
            // token 不存在
            if (authorization == null || "".equals(authorization)) {
                response.setStatus(301);
                response.getWriter().print(objectMapper.writeValueAsString(responseEx));
                return false;
            }

            // 根据token去redis中查
            YdlUserLogin user = redisTemplate.getObject(YdlConstants.TOKEN_PREFIX + authorization, new TypeReference<YdlUserLogin>() {
            });
            if (user == null) {
                response.setStatus(301);
                response.getWriter().print(objectMapper.writeValueAsString(responseEx));
                return false;
            }
        }
        return true;
    }
}
