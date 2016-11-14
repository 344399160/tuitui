package com.qbin.tuitui.goods.controller;

import com.qbin.tuitui.goods.service.GoodsService;
import com.taobao.api.ApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：用户信息
 * author qiaobin   2016/10/31 18:04.
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value = "/getgoods", method = RequestMethod.GET)
    public String index(){
        try {
            return goodsService.getGoods();
        } catch (ApiException e) {
            return null;
        }

    }

    @RequestMapping(value = "/uatm", method = RequestMethod.GET)
    public String tbkUatmFavorites(){
        try {
            return goodsService.tbkUatmFavorites();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/favorites", method = RequestMethod.GET)
    public String favorites(){
        try {
            return goodsService.favorites();
        } catch (Exception e) {
            return null;
        }
    }

    @RequestMapping(value = "/tqg", method = RequestMethod.GET)
    public String tqg(){
        try {
            return goodsService.tqg();
        } catch (Exception e) {
            return null;
        }
    }

}
