package com.qbin.crawlers.common.globalconst;

/**
 * 描述：微信登陆常量
 * author qiaobin   2016/10/12 19:32.
 */
public final class WeiXinConst {
    //获取UUID地址
    public final static String url_getuuid="https://login.weixin.qq.com/jslogin?appid=wx782c26e4c19acffb&redirect_uri=https%3A%2F%2Fwx.qq.com%2Fcgi-bin%2Fmmwebwx-bin%2Fwebwxnewloginpage&fun=new&lang=zh_CN&_={t}";
    //获取微信二维码
    public final static String url_login2weima="https://login.weixin.qq.com/qrcode/{}?t=webwx";
    //轮询查看是否已扫描二维码
    public final static String url_check_login="https://login.weixin.qq.com/cgi-bin/mmwebwx-bin/login?uuid={}&tip=1&_={t}";
    //初始化
    public final static String url_init = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxinit?r={t}";
    //联系人
    public final static String url_contactlist = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxgetcontact?pass_ticket={ticket}&r={t}";
    //群
    public final static String url_qunlist = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxbatchgetcontact?type=ex&r={t}&pass_ticket={ticket}";

    public final static String url_synccheck = "https://webpush.weixin.qq.com/cgi-bin/mmwebwx-bin/synccheck?callback=jQuery18309326978388708085_1377482079946&r={t}&sid={0}&uin={1}&deviceid={2}&synckey={3}&_={t}";

    public final static String url_msglist = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsync?sid={}&r={t}";
    //发送消息
    public final static String url_sendMsg = "https://wx.qq.com/cgi-bin/mmwebwx-bin/webwxsendmsg?lang=zh_CN&pass_ticket={t}";
}
