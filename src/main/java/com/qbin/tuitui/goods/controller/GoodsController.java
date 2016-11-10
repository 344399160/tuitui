package com.qbin.tuitui.goods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 描述：用户信息
 * author qiaobin   2016/10/31 18:04.
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "index";
    }

}
