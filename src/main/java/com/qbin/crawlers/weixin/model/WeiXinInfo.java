package com.qbin.crawlers.weixin.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * 描述：微信实体
 * author qiaobin   2016/10/12 19:44.
 */
@Data
public class WeiXinInfo {
    private String wxuin;
    private String wxsid;
    private String webwxuvid;
    private String deviceid;
    private String syncKeyStr;
    private JSONObject SyncKey;
    private String SKey;
    private JSONObject ujson;
    private String name;   //帐号
    private String nickName;//昵称
    private boolean connect = false;
    private String pass_ticket;
    private String cookie;


    private String uuid;
    private String QRCodeUrl;  //二维码下载地址
    private String initContent;
}
