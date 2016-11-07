package com.qbin.crawlers.register.model;

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
