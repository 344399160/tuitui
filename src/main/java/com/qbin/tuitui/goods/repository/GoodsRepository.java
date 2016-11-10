package com.qbin.tuitui.goods.repository;

import com.qbin.tuitui.goods.model.TaoKeGoods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Administrator on 2016/11/9.
 */
public interface GoodsRepository extends JpaRepository<TaoKeGoods, String>, JpaSpecificationExecutor<TaoKeGoods> {

}
