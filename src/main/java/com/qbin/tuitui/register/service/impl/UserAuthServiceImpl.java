package com.qbin.tuitui.register.service.impl;

import com.qbin.tuitui.register.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 描述：用户信息
 * author qiaobin   2016/10/31 17:10.
 */
@Service
public class UserAuthServiceImpl implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    private org.springframework.security.core.userdetails.User userdetails;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        com.qbin.tuitui.register.model.User user = userRepository.findByUsername(username);

        userdetails = new User(user.getUserName(),
                user.getPassword(),
                enabled,
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                user.getAuthorities()
        );

        return userdetails;
    }
}
