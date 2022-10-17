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
public class LogAspect /* implements BeanFactoryAware */ {

    @Resource
    private YdlOperLogService ydlOperLogService;

    // @Resource
    // private ThreadPoolExecutor threadPoolExecutor;

    // private BeanFactory beanFactory;

    // @Override
    // public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
    //     this.beanFactory = beanFactory;
    // }


    // 最终通知
    @AfterReturning(value = "@annotation(loggger)")
    public void afterReturningAdvice(JoinPoint joinPoint, Log loggger) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        YdlOperLog ydlOperLog = createLoggerEntity(request, joinPoint, loggger, null);
        ydlOperLogService.insert(ydlOperLog);

        // logHandler被Async注解修饰标识是一个异步方法，会生成一个代理
        // 需要通过这个代理对象去调用logHandler
        // LogAspect logAspect = beanFactory.getBean(this.getClass());
        // logAspect.logHandler(ydlOperLog);


    }


    // 异常通知
    @AfterThrowing(value = "@annotation(logger)", throwing = "exception")
    public void afterReturningAdvice(JoinPoint joinPoint, Log logger, Exception exception) {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        YdlOperLog ydlOperLog = createLoggerEntity(request, joinPoint, logger, exception);
        ydlOperLogService.insert(ydlOperLog);

        // LogAspect logAspect = beanFactory.getBean(this.getClass());
        // logAspect.logHandler(ydlOperLog);
    }


    // 日志核心处理器
    // @Async(value = "executor")
    // public void logHandler(YdlOperLog ydlOperLog) {
    //     try {
    //         Thread.sleep(10000);
    //     } catch (InterruptedException e) {
    //         e.printStackTrace();
    //     }
    //     ydlOperLogService.insert(ydlOperLog);
    //     // 实现异步 日志提交信息
    //     // threadPoolExecutor.execute(() -> {});
    // }


    // 组装 YdlOperLog对象
    private YdlOperLog createLoggerEntity(HttpServletRequest request, JoinPoint joinPoint, Log log, Exception exception) {
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
        return ydlOperLog;
    }
}
