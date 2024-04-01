package com.maximgaiduchek.eshop.security.services.interfaces;

import io.jsonwebtoken.Claims;

public interface JwtProvider {

    void validateAccessToken(String accessToken);

    Claims getAccessClaims(String token);
}
