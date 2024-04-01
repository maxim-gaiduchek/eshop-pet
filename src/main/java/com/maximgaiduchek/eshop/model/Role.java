package com.maximgaiduchek.eshop.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ROLE_USER("ROLE_USER"),
    ROLE_SELLER("ROLE_SELLER"),
    ROLE_MANAGER("ROLE_MANAGER");

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
