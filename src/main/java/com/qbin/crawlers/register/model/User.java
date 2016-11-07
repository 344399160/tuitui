package com.qbin.crawlers.register.model;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * 描述：用户
 * author qiaobin   2016/10/31 15:23.
 */
@Data
@Entity
@Table(name = "`USER`")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "`USERID`")
    private String userId;

    @Column(name = "`USERNAME`")
    private String userName;

    @Column(name = "`PHONENUMBER`")
    private String phoneNumber;

    @Column(name = "`PASSWORD`")
    private String password;

    @Column(name = "`EMAIL`")
    private String email;

    @Column(name = "`QQ`")
    private String qq;

    @Column(name = "`WEIXIN`")
    private String WeiXin;

    @Column(name = "`LEVEL`")
    private int level;

    @ManyToOne
    private Role role;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authorities")
    @Column(name = "user_authority")
    @Enumerated(EnumType.STRING)
    private Set<Authority> authorities = Sets.newHashSet(Authority.USER);

    /*
   *  创建时间
   * */
    @Setter
    @Column(name = "`CREATETIME`",nullable = false, updatable = false, insertable = false, columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    /*
    *  最后修改时间
    * */
    @Setter
    @Column(name = "`LASTMODIFYTIME`",nullable = false, updatable = true, insertable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifyTime;
}
