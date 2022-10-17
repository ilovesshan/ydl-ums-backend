package com.ilovesshan.common;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/17
 * @description:
 */

// @Configuration
public class ThreadPoolExecutorService {

    // @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(10, 20, 120, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(32), new ThreadPoolExecutor.AbortPolicy());
    }
}
