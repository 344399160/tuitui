package com.qbin.tuitui.weixin.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qbin.tuitui.common.cache.service.RedisTemplate;
import com.qbin.tuitui.common.globalconst.WeiXinConst;
import com.qbin.tuitui.common.util.*;
import com.qbin.tuitui.common.util.HttpRequestUtils;
import com.qbin.tuitui.weixin.model.WeiXinInfo;
import com.qbin.tuitui.weixin.model.WeiXinUser;
import com.qbin.tuitui.weixin.service.WeiXinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：微信通讯协议登录
 * author qiaobin   2016/10/12 19:03.
 */
@Service
public class WeiXinServiceImpl implements WeiXinService{

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 获取二维码
     */
    @Override
    public WeiXinInfo getQRCode() {
        //1.获取UUID
        String toGetUUIDUrl = WeiXinConst.url_getuuid.replace("{t}", ""+System.currentTimeMillis());
        String uuidUrl = HttpRequestUtils.sendGet(toGetUUIDUrl, null);
        String uuid = uuidUrl.substring(uuidUrl.indexOf("\"")+1, uuidUrl.lastIndexOf("\""));
        //2.取得登录二维码
        String url_login = WeiXinConst.url_login2weima.replace("{}", uuid);
        String QRCode = HttpRequestUtils.dowmImg(url_login);
        WeiXinInfo info = new WeiXinInfo();
        info.setUuid(uuid);
        info.setQRCodeUrl(QRCode);
        return info;
    }

    /**
     * 返回登录信息
     */
    @Override
    public WeiXinInfo login(WeiXinInfo info) throws Exception{
        //3.轮询手机端是否已经扫描二维码并确认在Web端登录
        String login_url = WeiXinUtil.check_login(info.getUuid());
        //4.登陆成功，获取cookies及关键参数
        com.blade.kit.http.HttpRequest request = com.blade.kit.http.HttpRequest.get(login_url+"&fun=new");
        info.setCookie(HttpRequestUtils.getCookie(request));
        String res = request.body();
        Map<String, String> cookies = Utils.getCookies(login_url);
        info.setWxuin(cookies.get("wxuin"));
        info.setWxsid(cookies.get("wxsid"));
        info.setWebwxuvid(cookies.get("webwxuvid"));
        info.setDeviceid("e960817075982756");
        info.setPass_ticket(RegixUtil.match("<pass_ticket>(\\S+)</pass_ticket>", res));
        //4.调用初始化url(初始化界面等信息)
        String intUrl = WeiXinConst.url_init.replace("{t}", ""+System.currentTimeMillis());
        //5.发送Post请求
        String initContent = Utils.postString(intUrl, WeiXinUtil.getPostString(info));
        info.setInitContent(initContent);
        JSONObject init =  JSONObject.parseObject(initContent);
        JSONObject user  = init.getJSONObject("User");
        info.setUjson(user);
        info.setName(user.getString("UserName"));
        info.setNickName(user.getString("NickName"));

        JSONObject SyncKey = init.getJSONObject("SyncKey");
        info.setSyncKey(SyncKey);
        info.setSyncKeyStr(WeiXinUtil.getSyncKey(init));
        info.setSKey(init.getString("SKey"));
        info.setConnect(true);
        redisTemplate.set(info.getName(), Json.toJsonString(info));
        //每10秒发送一次请求保持连接
//        QuartsUtil.addJob(info.getName(), WeixinSyncSchedule.class, "0/10 * * * * ?");
        return info;
    }

    /**
     * 联系人列表
     */
    @Override
    public List<WeiXinUser> contactList(WeiXinInfo info) {
        //获取好友列表
//        String contactListUrl = WeiXinConst.url_contactlist.replace("{t}", ""+System.currentTimeMillis()).replace("{ticket}", info.getPass_ticket());
//        String friendsList = HttpRequestUtils.sendPost(contactListUrl, WeiXinUtil.getPostString(info));
//        JSONObject friends =  JSONObject.parseObject(friendsList);
//        JSONObject memberList  = friends.getJSONObject("MemberList");
//        System.out.println(memberList);


        JSONObject friends =  JSONObject.parseObject(info.getInitContent());
        String ContactListString = friends.getString("ContactList");
        List<WeiXinUser> contactList = null;
        try {
            contactList = (List<WeiXinUser>) Json.toObjectList(ContactListString, WeiXinUser.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contactList;
    }

    public void qunList(WeiXinInfo info){
        String qunListUrl = WeiXinConst.url_qunlist.replace("{t}", ""+System.currentTimeMillis()).replace("{ticket}", info.getPass_ticket());
//        String friendsList = HttpRequestUtils.sendPost(qunListUrl, WeiXinUtil.getPostString(info));
        String post = WeiXinUtil.getPostString(info);
        String post1 = post.toString();
        post1 = ""+post1.substring(0, post1.length()-1) +",\"ContactList\":'"+info.getName()+"'}";
        String friendsList = Utils.postString(qunListUrl, post1);
        System.out.println(friendsList);
    }

    /**
     * 发送文字信息
     */
    @Override
    public String sendTextMessage(String wxUserId, String toName, String content) throws Exception{
        WeiXinInfo weiXinInfo = (WeiXinInfo) Json.toObject(redisTemplate.get(wxUserId).toString(), WeiXinInfo.class);
        return WeiXinUtil.sendMsg(toName, content, weiXinInfo);
    }

}
