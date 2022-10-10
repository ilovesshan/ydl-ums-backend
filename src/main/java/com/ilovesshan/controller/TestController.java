package com.ilovesshan.controller;

import com.ilovesshan.common.R;
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

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping()
    public R test() {
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("id", "1111111");
        hashMap.put("username", "ilovesshan");
        return R.success(hashMap);
    }

}
