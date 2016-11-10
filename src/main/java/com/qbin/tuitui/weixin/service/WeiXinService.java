package com.qbin.tuitui.weixin.service;

import com.qbin.tuitui.weixin.model.WeiXinInfo;
import com.qbin.tuitui.weixin.model.WeiXinUser;

import java.util.List;

/**
 * 描述：微信业务逻辑
 * author qiaobin   2016/10/18 15:47.
 */
public interface WeiXinService {
    /**
     * 获取二维码
     */
    public WeiXinInfo getQRCode();

    /**
     * 返回登录信息
     */
    public WeiXinInfo login(WeiXinInfo info) throws Exception;

    /**
     * 联系人列表
     */
    public List<WeiXinUser> contactList(WeiXinInfo info);

    public void qunList(WeiXinInfo info);

    /**
     * 发送文字信息
     */
    public String sendTextMessage(String wxUserId, String toName, String content) throws Exception;
}
