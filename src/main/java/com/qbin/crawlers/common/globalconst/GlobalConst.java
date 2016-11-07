package com.qbin.crawlers.common.globalconst;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述：全局变量
 * author qiaobin   2016/10/15 11:03.
 */
public class GlobalConst {

    private final static String GOODSID = "商品id";
    private final static String GOODSNAME = "商品名称";
    private final static String GOODSPICTURE = "商品主图";
    private final static String GOODSURL = "商品详情页链接地址";
    private final static String STORENAME = "店铺名称";
    private final static String PRICE = "商品价格(单位：元)";
    private final static String SALESVOLUME = "商品月销量";
    private final static String INCOMERATIO = "收入比率(%)";
    private final static String COMMISSION = "佣金";
    private final static String WANGWANG = "卖家旺旺";
    private final static String TBKRUL = "淘宝客链接";
    private final static String COUPONCOUNT = "优惠券总量";
    private final static String LEFTCOUPON = "优惠券剩余量";
    private final static String COUPONPRICE = "优惠券面额";
    private final static String COUPONBEGINTIME = "优惠券开始时间";
    private final static String COUPONENDTIME = "优惠券结束时间";
    private final static String COUPONURL = "优惠券链接";

    public final static Map<String, String> GOODSMAP = new HashMap<>();
    static {
        GOODSMAP.put(GOODSID, "goodsId");
        GOODSMAP.put(GOODSNAME, "goodsName");
        GOODSMAP.put(GOODSPICTURE, "goodsPicture");
        GOODSMAP.put(GOODSURL, "goodsUrl");
        GOODSMAP.put(STORENAME, "storeName");
        GOODSMAP.put(PRICE, "price");
        GOODSMAP.put(SALESVOLUME, "salesVolumn");
        GOODSMAP.put(INCOMERATIO, "incomeRatio");
        GOODSMAP.put(COMMISSION, "commission");
        GOODSMAP.put(WANGWANG, "wangwang");
        GOODSMAP.put(TBKRUL, "tbkUrl");
        GOODSMAP.put(COUPONCOUNT, "couponCount");
        GOODSMAP.put(LEFTCOUPON, "leftCoupon");
        GOODSMAP.put(COUPONPRICE, "couponPrice");
        GOODSMAP.put(COUPONBEGINTIME, "couponBeginTime");
        GOODSMAP.put(COUPONENDTIME, "couponEndTime");
        GOODSMAP.put(COUPONURL, "couponUrl");
    }

}
