package com.maximgaiduchek.eshop.security.services.impl;

import com.maximgaiduchek.eshop.exceptions.AuthorizationException;
import com.maximgaiduchek.eshop.exceptions.exceptioncodes.AccessExceptionCodes;
import com.maximgaiduchek.eshop.security.services.interfaces.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Objects;

@Slf4j
@Service
public class JwtProviderImpl implements JwtProvider {

    private final SecretKey jwtAccessSecret;
    private final SecretKey jwtRefreshSecret;

    public JwtProviderImpl(
            @Value("${jwt.secret.access}") String jwtAccessSecret,
            @Value("${jwt.secret.refresh}") String jwtRefreshSecret
    ) {
        this.jwtAccessSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtAccessSecret));
        this.jwtRefreshSecret = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtRefreshSecret));
    }

    @Override
    public void validateAccessToken(String accessToken) {
        validateToken(accessToken, jwtAccessSecret);
    }

    private void validateToken(String token, Key secret) throws AuthorizationException {
        if (Objects.isNull(token)) {
            throw new AuthorizationException(AccessExceptionCodes.TOKEN_REQUIRED);
        }
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
        } catch (ExpiredJwtException expEx) {
            throw new AuthorizationException(AccessExceptionCodes.TOKEN_EXPIRED);
        } catch (Exception mjEx) {
            throw new AuthorizationException(AccessExceptionCodes.TOKEN_INVALID);
        }
    }

    @Override
    public Claims getAccessClaims(String token) {
        return getClaims(token, jwtAccessSecret);
    }

    private Claims getClaims(String token, Key secret) {
        return Jwts.parserBuilder()
                .setSigningKey(secret)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
