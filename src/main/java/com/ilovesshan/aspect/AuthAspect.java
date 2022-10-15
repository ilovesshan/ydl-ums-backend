package com.ilovesshan.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/15
 * @description:
 */

@Component
@Aspect
public class AuthAspect {
    @Before("@annotation(HasPermission))")
    public void beforePermissionAdvice(JoinPoint joinPoint) {

    }


    @Before("@annotation(HasRole))")
    public void beforeRoleAdvice(JoinPoint joinPoint) {

    }

}
