package com.qbin.tuitui.register.model;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ADMIN,
    USER,
    VIP;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
