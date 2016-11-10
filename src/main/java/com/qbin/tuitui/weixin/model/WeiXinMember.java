package com.qbin.tuitui.weixin.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 描述：TODO
 * author qiaobin   2016/10/13 13:17.
 */
@Data
public class WeiXinMember {
    @JsonProperty("AttrStatus")
    private String attrStatus;
    @JsonProperty("DisplayName")
    private String displayName;
    @JsonProperty("KeyWord")
    private String keyWord;
    @JsonProperty("MemberStatus")
    private String memberStatus;
    @JsonProperty("NickName")
    private String nickName;
    @JsonProperty("PYInitial")
    private String pyinitial;
    @JsonProperty("PYQuanPin")
    private String pyquanPin;
    @JsonProperty("RemarkPYInitial")
    private String remarkPYInitial;
    @JsonProperty("RemarkPYQuanPin")
    private String remarkPYQuanPin;
    @JsonProperty("Uin")
    private String uin;
    @JsonProperty("UserName")
    private String userName;
}
