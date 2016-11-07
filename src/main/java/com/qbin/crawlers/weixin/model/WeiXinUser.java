package com.qbin.crawlers.weixin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 描述：TODO
 * author qiaobin   2016/10/13 13:13.
 */
@Data
public class WeiXinUser {
    @JsonProperty("Alias")
    private String alias;
    @JsonProperty("AppAccountFlag")
    private String appAccountFlag;
    @JsonProperty("AttrStatus")
    private String attrStatus;
    @JsonProperty("ChatRoomId")
    private String chatRoomId;
    @JsonProperty("City")
    private String city;
    @JsonProperty("ContactFlag")
    private String contactFlag;
    @JsonProperty("DisplayName")
    private String displayName;
    @JsonProperty("EncryChatRoomId")
    private String encryChatRoomId;
    @JsonProperty("HeadImgUrl")
    private String headImgUrl;
    @JsonProperty("HideInputBarFlag")
    private String hideInputBarFlag;
    @JsonProperty("KeyWord")
    private String keyWord;
    @JsonProperty("MemberCount")
    private String memberCount;
    @JsonProperty("MemberList")
    private List<WeiXinMember> memberList;
    @JsonProperty("NickName")
    private String nickName;
    @JsonProperty("OwnerUin")
    private String ownerUin;
    @JsonProperty("PYInitial")
    private String pyinitial;
    @JsonProperty("PYQuanPin")
    private String pyquanPin;
    @JsonProperty("Province")
    private String province;
    @JsonProperty("RemarkName")
    private String remarkName;
    @JsonProperty("RemarkPYInitial")
    private String remarkPYInitial;
    @JsonProperty("RemarkPYQuanPin")
    private String remarkPYQuanPin;
    @JsonProperty("Sex")
    private String sex;
    @JsonProperty("Signature")
    private String signature;
    @JsonProperty("SnsFlag")
    private String snsFlag;
    @JsonProperty("StarFriend")
    private String starFriend;
    @JsonProperty("Statues")
    private String statues;
    @JsonProperty("Uin")
    private String uin;
    @JsonProperty("UniFriend")
    private String uniFriend;
    @JsonProperty("UserName")
    private String userName;
    @JsonProperty("VerifyFlag")
    private String verifyFlag;
}
