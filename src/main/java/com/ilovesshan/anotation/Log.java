package com.ilovesshan.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/16
 * @description:
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    /**
     * 业务模块
     */
    String business_module();

    /**
     * 具体描述
     */
    String business_describe();

    /**
     * 业务类型
     */
    String business_type();
}
