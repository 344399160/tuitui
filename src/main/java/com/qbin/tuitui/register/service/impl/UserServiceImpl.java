package com.qbin.tuitui.register.service.impl;

import com.qbin.tuitui.register.repository.UserRepository;
import com.qbin.tuitui.register.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 描述：用户业务处理
 * author qiaobin   2016/10/31 18:08.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

}
