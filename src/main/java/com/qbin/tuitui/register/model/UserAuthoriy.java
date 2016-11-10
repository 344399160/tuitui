package com.qbin.tuitui.register.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 描述：用户权限表
 * author qiaobin   2016/10/11 14:39.
 */
@Data
@Entity
@Table(name = "`USER_AUTHORITIES`")
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthoriy {

    /**
     *  用户ID
     */
    @Id
    @Column(name="`USER_ID`")
    private String userId;

    /**
     *  权限
     */
    @Column(name="`USER_AUTHORITY`")
    private String authority;

}
