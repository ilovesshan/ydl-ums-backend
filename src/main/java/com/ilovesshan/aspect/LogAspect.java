package com.ilovesshan.aspect;

import com.ilovesshan.anotation.Log;
import com.ilovesshan.pojo.YdlOperLog;
import com.ilovesshan.service.YdlOperLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/16
 * @description:
 */

@Component
@Aspect
@Slf4j
public class LogAspect {

    @Resource
    private YdlOperLogService ydlOperLogService;


    // 最终通知
    @AfterReturning(value = "@annotation(log)")
    public void afterReturningAdvice(JoinPoint joinPoint, Log log) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        logHandler(request, joinPoint, log, null);
    }


    // 异常通知
    @AfterThrowing(value = "@annotation(log)", throwing = "exception")
    public void afterReturningAdvice(JoinPoint joinPoint, Log log, Exception exception) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        logHandler(request, joinPoint, log, exception);
    }


    // 日志核心处理器
    private void logHandler(HttpServletRequest request, JoinPoint joinPoint, Log log, Exception exception) {
        YdlOperLog ydlOperLog = new YdlOperLog();

        ydlOperLog.setOperIp(request.getRemoteAddr());
        ydlOperLog.setOperName(request.getHeader("username"));
        ydlOperLog.setOperUrl(request.getRequestURI());
        ydlOperLog.setMethod(joinPoint.getSignature().getName());
        ydlOperLog.setRequestMethod(request.getMethod());
        if (exception != null) {
            ydlOperLog.setErrormsg(exception.getMessage());
            ydlOperLog.setStatus(500);
        } else {
            ydlOperLog.setStatus(200);
        }
        ydlOperLog.setBusinessModule(log.business_module());
        ydlOperLog.setBusinessType(log.business_type());
        ydlOperLog.setBusinessDescribe(log.business_describe());
        ydlOperLog.setOpertime(new Date());

        // 提交信息
        ydlOperLogService.insert(ydlOperLog);
    }
}
