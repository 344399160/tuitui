package com.qbin.crawlers.register.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * 描述：用户组
 * author qiaobin   2016/10/31 15:46.
 */
@Data
@Entity
@Table(name = "`ROLE`")
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @Column(name = "`ROLEID`")
    private String roleId;

    @Column(name = "`NAME`", nullable = false, unique = true)
    private String name;

    @Column(name = "`DESCRIBE`")
    private String describe;

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
