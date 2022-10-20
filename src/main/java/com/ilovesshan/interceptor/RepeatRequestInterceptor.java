package com.ilovesshan.interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ilovesshan.anotation.Repeat;
import com.ilovesshan.common.R;
import com.ilovesshan.common.RedisTemplate;
import com.ilovesshan.constant.YdlConstants;
import com.ilovesshan.handler.CustomObjectMapper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/20
 * @description:
 */

public class RepeatRequestInterceptor implements HandlerInterceptor {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CustomObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断是不是一个方法
        if (handler instanceof HandlerMethod) {
            // 判断该方法有没有@Repest注解
            Repeat annotation = (((HandlerMethod) handler)).getMethod().getAnnotation(Repeat.class);
            if (annotation != null && isRepeatable(request, annotation)) {
                R responseEx = R.fail("请求失败，重复的请求提交", null);
                response.getWriter().write(objectMapper.writeValueAsString(responseEx));
            } else {
                return true;
            }
        }
        return true;
    }

    // 判断该请求是不是重复请求
    private boolean isRepeatable(HttpServletRequest request, Repeat annotation) throws JsonProcessingException {
        // 获取请求url
        String url = request.getRequestURI();

        // 获取token
        String token = request.getHeader("Authorization");

        // 获取请求参数并 组装成字符串
        String newParameters = objectMapper.writeValueAsString(request.getParameterMap());

        // 拼接Key
        String key = YdlConstants.REPEAT_SUFFIX + token + url;

        // 根据key去redis中查询，查到了就是重复请求
        Object oldParameters = redisTemplate.getObject(key, new TypeReference<Object>() {});
        if (oldParameters != null && newParameters.equals(newParameters)) {
            return true;
        }
        // 将key设置进去(注意过期时间)
        redisTemplate.set(key, newParameters, annotation.value());
        return false;
    }

}
