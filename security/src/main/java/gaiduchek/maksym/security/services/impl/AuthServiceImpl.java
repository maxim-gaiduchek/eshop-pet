package gaiduchek.maksym.security.services.impl;

import gaiduchek.maksym.security.constants.JwtClaimsConstants;
import gaiduchek.maksym.security.dto.JwtRequest;
import gaiduchek.maksym.security.dto.JwtResponse;
import gaiduchek.maksym.security.exceptions.AccessException;
import gaiduchek.maksym.security.exceptions.exceptioncodes.AccessExceptionCodes;
import gaiduchek.maksym.security.services.interfaces.AuthService;
import gaiduchek.maksym.security.services.interfaces.JwtProvider;
import gaiduchek.maksym.security.services.interfaces.UserAuthService;
import gaiduchek.maksym.security.services.interfaces.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final UserAuthService userAuthService;

    @Override
    public JwtResponse login(JwtRequest authRequest) {
        var user = userService.getByEmailOrThrow(authRequest.getLogin());
        var userAuth = userAuthService.getByUserIdOrThrow(user.getId());
        var requestPassword = Optional.ofNullable(authRequest.getPassword())
                .orElse("")
                .toLowerCase();
        if (!userAuth.getPassword().equals(requestPassword)) {
            throw new AccessException(AccessExceptionCodes.INVALID_PASSWORD);
        }
        var accessToken = jwtProvider.generateAccessToken(userAuth, user);
        var refreshToken = jwtProvider.generateRefreshToken(userAuth);
        userAuth.getRefreshTokens().add(refreshToken);
        userAuthService.save(userAuth);
        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userId(user.getId())
                .role(user.getRole())
                .build();
    }

    @Override
    public void logout(String refreshToken) {
        var user = userAuthService.getByRefreshTokenOrThrow(refreshToken);
        user.getRefreshTokens().remove(refreshToken);
        userAuthService.save(user);
    }

    @Override
    public JwtResponse getAccessToken(String refreshToken) {
        if (jwtProvider.isAccessTokenValid(refreshToken)) {
            return new JwtResponse();
        }
        var claims = jwtProvider.getRefreshClaims(refreshToken);
        var userId = claims.get(JwtClaimsConstants.USER_ID_KEY, Long.class);
        var userAuth = userAuthService.getByUserIdOrThrow(userId);
        var user = userService.getByIdOtThrow(userId);
        var refreshTokens = userAuth.getRefreshTokens();
        if (refreshTokens == null || !refreshTokens.contains(refreshToken)) {
            return new JwtResponse();
        }
        var accessToken = jwtProvider.generateAccessToken(userAuth, user);
        return JwtResponse.builder()
                .accessToken(accessToken)
                .userId(userAuth.getUserId())
                .role(user.getRole())
                .build();
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        if (!jwtProvider.isRefreshTokenValid(refreshToken)) {
            throw new AccessException(AccessExceptionCodes.INVALID_JWT);
        }
        final Claims claims = jwtProvider.getRefreshClaims(refreshToken);
        final Long id = claims.get(JwtClaimsConstants.USER_ID_KEY, Long.class);
        var userAuth = userAuthService.getByUserIdOrThrow(id);
        var user = userService.getByIdOtThrow(id);
        var refreshTokens = userAuth.getRefreshTokens();
        if (refreshTokens == null || !refreshTokens.contains(refreshToken)) {
            throw new AccessException(AccessExceptionCodes.INVALID_JWT);
        }
        final String accessToken = jwtProvider.generateAccessToken(userAuth, user);
        final String newRefreshToken = jwtProvider.generateRefreshToken(userAuth);
        userAuth.getRefreshTokens().add(newRefreshToken);
        userAuthService.save(userAuth);
        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .userId(userAuth.getUserId())
                .role(user.getRole())
                .build();
    }
}
