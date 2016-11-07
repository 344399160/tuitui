package com.qbin.crawlers.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qbin.crawlers.common.globalconst.WeiXinConst;
import com.qbin.crawlers.weixin.model.WeiXinInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 描述：微信登陆工具
 * author qiaobin   2016/10/12 19:31.
 */
public final class WeiXinUtil {
    /**
      * 功能描述：验证是否登陆
      * @author qiaobin
      * @date 2016/10/12  19:35
      */
    public static String check_login(String uuid){
        String url_check = WeiXinConst.url_check_login.replace("{}", uuid);

        url_check = url_check.replace("{t}", ""+System.currentTimeMillis());
        int c = 0;
        String login_url = null;
        while(login_url == null){
            String res = HttpRequestUtils.sendGet(url_check, null);
            Pattern pat = Pattern.compile("window.redirect_uri=\"(.+)\";");
            Matcher mat = pat.matcher(res);
            if(mat.find()){
                login_url = mat.group(1);
            }
            c++;
        }
        System.out.println("经历了"+c+"次轮询");
        return login_url;
    }

    /**
      * 功能描述：发送消息
      * @author qiaobin
      * @date 2016/10/13  9:59
      * @param toName 发送给
      * @param content 内容
      * @param info 用户登录信息
      */
    public static String sendMsg(String toName, String content, WeiXinInfo info){
        System.setProperty("jsse.enableSNIExtension", "false");
        String url = WeiXinConst.url_sendMsg.replace("{t}", info.getPass_ticket());
        JSONObject post = new JSONObject();
        JSONObject BaseRequest = new JSONObject();
        BaseRequest.put("DeviceID", info.getDeviceid());
        BaseRequest.put("Sid", info.getWxsid());
        BaseRequest.put("Skey", info.getSKey());
        BaseRequest.put("Uin", info.getWxuin());
        post.put("BaseRequest", BaseRequest);
        JSONObject msg = new JSONObject();
        msg.put("ClientMsgId", System.currentTimeMillis());
        msg.put("Content", content);
        msg.put("FromUserName", info.getName());
        msg.put("LocalID", System.currentTimeMillis());
        msg.put("ToUserName", toName);
        msg.put("Type",  1);
        post.put("Msg", msg);
        post.put("Scene", 0);

        String result = HttpRequestUtils.sendPost(url, post.toString());
        if(result != null){
            JSONObject r =   JSONObject.parseObject(result);
            if(r.getJSONObject("BaseResponse").getInteger("Ret") == 0){
                return "发送成功";
            }else{
                return "发送失败";
            }
        }else{
            //多次测试此时是发送成功的
            return "发送成功";
        }
    }

    /**
     * 功能描述：获取同步KEY
     * @author qiaobin
     * @date 2016/10/18  16:25
     * @param json
     */
    public static String getSyncKey(JSONObject json){
        JSONObject SyncKey = json.getJSONObject("SyncKey");
        JSONArray Listarr = SyncKey.getJSONArray("List");
        String synckey = "";
        for(int i = 0;i<Listarr.size(); i++){
            JSONObject jj = Listarr.getJSONObject(i);
            synckey = synckey + jj.get("Key")+"_"+jj.get("Val")+"|"  ;
        }
        synckey = synckey.substring(0, synckey.length() - 2);
        return synckey;
    }

    /**
      * 功能描述：拼COOKIE
      * @author qiaobin
      * @date 2016/10/18  16:25
      * @param info
      */
    public static String getPostString(WeiXinInfo info) {
        JSONObject post = new JSONObject();
        JSONObject BaseRequest = new JSONObject();
        post.put("BaseRequest", BaseRequest);
        BaseRequest.put("Uin", info.getWxuin());
        BaseRequest.put("Sid", info.getWxsid());
        BaseRequest.put("Skey", info.getSKey());
        BaseRequest.put("DeviceID", info.getDeviceid());
        return post.toString();
    }
}
