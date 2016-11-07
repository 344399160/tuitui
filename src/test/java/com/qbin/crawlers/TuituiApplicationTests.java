package com.qbin.crawlers;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.qbin.crawlers.common.cache.service.RedisTemplate;
import com.qbin.crawlers.common.util.ExcelPaser;
import com.qbin.crawlers.common.util.Json;
import com.qbin.crawlers.weixin.model.WeiXinInfo;
import com.qbin.crawlers.weixin.service.WeiXinService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TuituiApplicationTests {

	@Autowired
	private WeiXinService weiXinService;
	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void tee() {
	}

	//获取二维码
	@Test
	public void getQRCode() {
		try {
			String code = Json.toJsonString(weiXinService.getQRCode());
			System.out.println(code);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	//登录
	@Test
	public void login() {
		try {
			String code = "{\"wxuin\":null,\"wxsid\":null,\"webwxuvid\":null,\"deviceid\":null,\"syncKeyStr\":null,\"ujson\":null,\"name\":null,\"nickName\":null,\"connect\":false,\"pass_ticket\":null,\"cookie\":null,\"uuid\":\"oY6rQWbPzg==\",\"initContent\":null,\"qrcodeUrl\":\"E:\\\\myProject\\\\tuituizhushou\\\\img.png\",\"syncKey\":null,\"skey\":null}";
			WeiXinInfo info = (WeiXinInfo)Json.toObject(code, WeiXinInfo.class);
			String init = Json.toJsonString(weiXinService.login(info));
			System.out.println(init);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//联系人列表
	@Test
	public void getContactList() {
		try {
			WeiXinInfo info = (WeiXinInfo) Json.toObject(redisTemplate.get("@bb0d4ae479c64d45fb82679ac4ede8d8").toString(), WeiXinInfo.class);
			String contactList = Json.toJsonString(weiXinService.contactList(info));
			System.out.println(contactList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//群组列表
	@Test
	public void getQunList() {
		try {
			WeiXinInfo info = (WeiXinInfo) Json.toObject(redisTemplate.get("@eca79fc6777df41316b0d4a9bf240a04").toString(), WeiXinInfo.class);
			weiXinService.qunList(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//发送消息
	@Test
	public void sendMessage() {
		try {
			String userName = "@ca2729bc49bf2927af68d24965e0fc1a";
			String toUser = "@3f8a506c04dd1b1b19027faddd56c911";
			String mes = weiXinService.sendTextMessage(userName, toUser, "11");
			System.out.println(mes);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void aa() {
		JSONObject post = new JSONObject();
		JSONObject BaseRequest = new JSONObject();
		post.put("BaseRequest", BaseRequest);
		BaseRequest.put("Uin", 1);
		BaseRequest.put("Sid", 2);
		BaseRequest.put("Skey", 3);
		BaseRequest.put("DeviceID", 3);
		String post1 = post.toString();
		post1 = ""+post1.substring(0, post1.length()-1) +",\"ContactList\":\"bluesubway\"}";
		System.out.println(post1);
	}

}
