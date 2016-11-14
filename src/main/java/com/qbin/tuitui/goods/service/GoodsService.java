package com.qbin.tuitui.goods.service;

import com.taobao.api.ApiException;

/**
 * Created by Administrator on 2016/11/9.
 */
public interface GoodsService {

    public String getGoods() throws ApiException;

    public String tbkUatmFavorites();

    public String favorites();

    public String tqg() throws Exception;
}
