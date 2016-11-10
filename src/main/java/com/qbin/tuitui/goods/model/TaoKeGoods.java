package com.qbin.tuitui.goods.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Administrator on 2016/11/9.
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "`TAOKEGOODS`")
public class TaoKeGoods {
    @Id
    private String id;

    @Column(name = "NAME")
    private String name;
}
