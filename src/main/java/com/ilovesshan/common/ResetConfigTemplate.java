
package com.ilovesshan.common;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/11
 * @description:
 */

@Component
public class ResetConfigTemplate {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}