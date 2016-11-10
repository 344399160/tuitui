package com.qbin.tuitui.register.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：用户信息
 * author qiaobin   2016/10/31 18:04.
 */
@RestController
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "/id", method = RequestMethod.POST)
    public String register(int phoneNumber, String password){
        System.out.println(phoneNumber);
        System.out.println(password);
        return "register";
    }
}
