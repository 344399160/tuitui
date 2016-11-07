package com.qbin.crawlers.register.repository;

import com.qbin.crawlers.register.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 描述：用户操作
 * author qiaobin   2016/10/31 17:07.
 */
public interface UserRepository extends JpaRepository<User, String>, JpaSpecificationExecutor<User> {

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    public User findByUsername(String userName);
}
