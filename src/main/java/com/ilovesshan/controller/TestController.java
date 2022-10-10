package com.ilovesshan.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.ilovesshan.common.R;
import com.ilovesshan.common.RedisTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: ilovesshan
 * @date: 2022/10/10
 * @description:
 */

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping()
    public R test() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("id", "1111111");
        hashMap.put("username", "ilovesshan");
        String setObject = redisTemplate.setObject("user", hashMap, -1L);
        log.info("setObject===========", setObject);
        HashMap<Object, Object> data = redisTemplate.getObject("user", new TypeReference<HashMap<Object, Object>>() {
        });
        return R.success(data);
    }
}
