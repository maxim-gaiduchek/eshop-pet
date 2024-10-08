package gaiduchek.maksym.security.services.interfaces;

import gaiduchek.maksym.security.dto.UserDto;
import gaiduchek.maksym.security.model.UserAuth;
import io.jsonwebtoken.Claims;

public interface JwtProvider {

    String generateAccessToken(UserAuth userAuth, UserDto user);

    String generateRefreshToken(UserAuth userAuth);

    boolean isAccessTokenValid(String token);

    boolean isRefreshTokenValid(String token);

    Claims getAccessClaims(String token);

    Claims getRefreshClaims(String token);
}
