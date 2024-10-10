package gaiduchek.maksym.api.security.services.interfaces;

import io.jsonwebtoken.Claims;

public interface JwtProvider {

    boolean isAccessTokenValid(String token);

    Claims getAccessClaims(String token);
}
