package com.qbin.crawlers.register.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：用户信息
 * author qiaobin   2016/10/31 18:04.
 */
@RestController
@RequestMapping
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String register(){
        System.out.println("login");
        return "login";
    }
}
