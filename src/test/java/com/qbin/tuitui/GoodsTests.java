package com.qbin.tuitui;

import com.qbin.tuitui.goods.service.GoodsService;
import com.taobao.api.ApiException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsTests {

	@Autowired
	private GoodsService goodsService;

	@Test
	public void tee() {
		try {
			goodsService.getGoods();
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}


}
