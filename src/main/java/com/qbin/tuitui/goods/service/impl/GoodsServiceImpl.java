package com.qbin.tuitui.goods.service.impl;

import com.qbin.tuitui.goods.service.GoodsService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.StringUtils;
import com.taobao.api.request.TbkItemGetRequest;
import com.taobao.api.request.TbkJuTqgGetRequest;
import com.taobao.api.request.TbkUatmFavoritesGetRequest;
import com.taobao.api.request.TbkUatmFavoritesItemGetRequest;
import com.taobao.api.response.TbkItemGetResponse;
import com.taobao.api.response.TbkJuTqgGetResponse;
import com.taobao.api.response.TbkUatmFavoritesGetResponse;
import com.taobao.api.response.TbkUatmFavoritesItemGetResponse;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2016/11/9.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    /**
     * 商品检索
     * @throws ApiException
     */
    public String getGoods() throws ApiException{
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
            req.setPageNo(1L);
            req.setPageSize(20L);
            rsp = client.execute(req);
            return rsp.getBody();
        } catch (ApiException e) {
            throw e;
        }
    }

    /**
     * 获取淘宝联盟选品库列表
     */
    public String tbkUatmFavorites() {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23517722", "80222a4b2873a2b4a236aa85419a4e5f");
        TbkUatmFavoritesGetRequest req = new TbkUatmFavoritesGetRequest();
        req.setPageNo(1L);
        req.setPageSize(20L);
        req.setFields("favorites_title,favorites_id,type");
        req.setType(2L);
        TbkUatmFavoritesGetResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return rsp.getBody();
    }

    /**
     * 获取淘宝联盟选品库的宝贝信息)
     */
    public String favorites() {
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23517722", "80222a4b2873a2b4a236aa85419a4e5f");
        TbkUatmFavoritesItemGetRequest req = new TbkUatmFavoritesItemGetRequest();
        req.setPlatform(1L);
        req.setPageSize(20L);
        req.setAdzoneId(65904740L);
        req.setUnid("3456");
        req.setFavoritesId(10001L);
        req.setPageNo(2L);
        req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick,shop_title,zk_final_price_wap,event_start_time,event_end_time,tk_rate,status,type");
        TbkUatmFavoritesItemGetResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return rsp.getBody();
    }

    /**
     * 淘抢购api
     */
    public String tqg() throws Exception{
        TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23517722", "80222a4b2873a2b4a236aa85419a4e5f");
        TbkJuTqgGetRequest req = new TbkJuTqgGetRequest();
//        req.setAdzoneId(63120969L);
        req.setAdzoneId(65904740L);
        req.setFields("click_url,pic_url,reserve_price,zk_final_price,total_amount,sold_num,title,category_name,start_time,end_time");
        req.setStartTime(StringUtils.parseDateTime("2016-11-11 00:00:00"));
        req.setEndTime(StringUtils.parseDateTime("2016-11-20 16:00:00"));
        req.setPageNo(1L);
        req.setPageSize(96L);
        TbkJuTqgGetResponse rsp = client.execute(req);
        return rsp.getBody();
    }


}
