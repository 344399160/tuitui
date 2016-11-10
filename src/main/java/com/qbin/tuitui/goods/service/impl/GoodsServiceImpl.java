package com.qbin.tuitui.goods.service.impl;

import com.qbin.tuitui.goods.service.GoodsService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.response.TbkItemGetResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/9.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    public void getGoods() throws ApiException{
        TbkItemGetResponse rsp = null;
        try {
            TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23517722", "80222a4b2873a2b4a236aa85419a4e5f");
            TbkItemGetRequest req = new TbkItemGetRequest();
            req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
            req.setQ("女装");
//            req.setCat("16,18");
//            req.setItemloc("杭州");
            req.setSort("tk_rate_des");
            req.setIsTmall(false);
            req.setIsOverseas(false);
//            req.setStartPrice(10L);
//            req.setEndPrice(10L);
//            req.setStartTkRate(123L);
//            req.setEndTkRate(123L);
            req.setPlatform(1L);
            req.setPageNo(Long.parseLong("1"));
            req.setPageSize(Long.parseLong("20"));
            rsp = client.execute(req);
            System.out.println(rsp.getBody());
        } catch (ApiException e) {
            throw e;
        }
    }
}
