package gaiduchek.maksym.security.services.impl;

import gaiduchek.maksym.security.constants.JwtClaimsConstants;
import gaiduchek.maksym.security.dto.UserDto;
import gaiduchek.maksym.security.model.UserAuth;
import gaiduchek.maksym.security.services.interfaces.JwtProvider;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

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
    public String generateAccessToken(UserAuth userAuth, UserDto user) {
        var now = LocalDateTime.now();
        var expirationInstant = now.plusSeconds(30).atZone(ZoneId.systemDefault()).toInstant();
        var expiration = Date.from(expirationInstant);
        return Jwts.builder()
                .setSubject(userAuth.getId().toString())
                .setExpiration(expiration)
                .signWith(jwtAccessSecret)
                .claim(JwtClaimsConstants.USER_ID_KEY, user.getId())
                .claim(JwtClaimsConstants.USER_EMAIl_KEY, user.getEmail())
                .claim(JwtClaimsConstants.USER_ROLE_KEY, user.getRole())
                .claim(JwtClaimsConstants.CREATED_AT, now.toString())
                .claim(JwtClaimsConstants.TYPE, "ACCESS")
                .compact();
    }

    @Override
    public String generateRefreshToken(UserAuth userAuth) {
        var now = LocalDateTime.now();
        var expirationInstant = now.plusMinutes(5).atZone(ZoneId.systemDefault()).toInstant();
        var expiration = Date.from(expirationInstant);
        return Jwts.builder()
                .setSubject(userAuth.getId().toString())
                .setExpiration(expiration)
                .signWith(jwtRefreshSecret)
                .claim(JwtClaimsConstants.USER_ID_KEY, userAuth.getId())
                .claim(JwtClaimsConstants.CREATED_AT, now.toString())
                .claim(JwtClaimsConstants.TYPE, "REFRESH")
                .compact();
    }

    @Override
    public boolean isAccessTokenValid(String token) {
        return validateToken(token, jwtAccessSecret);
    }

    private boolean validateToken(String token, Key secret) {
        try {
            Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException expEx) {
            log.error("Token expired {}, {}", token, expEx.getMessage());
        } catch (UnsupportedJwtException unsEx) {
            log.error("Unsupported jwt", unsEx);
        } catch (MalformedJwtException mjEx) {
            log.error("Malformed jwt", mjEx);
        } catch (Exception e) {
            log.error("invalid token", e);
        }
        return false;
    }

    @Override
    public boolean isRefreshTokenValid(String token) {
        return validateToken(token, jwtRefreshSecret);
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

    @Override
    public Claims getRefreshClaims(String token) {
        return getClaims(token, jwtRefreshSecret);
    }
}
