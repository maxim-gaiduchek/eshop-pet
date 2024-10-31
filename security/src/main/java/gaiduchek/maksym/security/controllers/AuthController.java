package gaiduchek.maksym.security.controllers;

import gaiduchek.maksym.security.dto.JwtRequest;
import gaiduchek.maksym.security.dto.JwtResponse;
import gaiduchek.maksym.security.dto.UserCredentialsDto;
import gaiduchek.maksym.security.exceptions.AccessException;
import gaiduchek.maksym.security.exceptions.exceptioncodes.AccessExceptionCodes;
import gaiduchek.maksym.security.services.interfaces.AuthService;
import gaiduchek.maksym.security.services.interfaces.UserAuthService;
import gaiduchek.maksym.security.utils.CookieUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static gaiduchek.maksym.security.constants.HeadersConstants.X_API_KEY;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Value("${jwt.cookie.age.refresh}")
    private int refreshTokenAge;

    private static final String REFRESH_TOKEN_COOKIE_NAME = "refreshToken";

    private final AuthService authService;
    private final UserAuthService userAuthService;

    @PostMapping("/login")
    public JwtResponse login(@RequestBody @Valid JwtRequest authRequest,
                             HttpServletResponse httpResponse) {
        var jwtResponse = authService.login(authRequest);
        var newRefreshTokenCookie = createRefreshTokenCookie(jwtResponse.getRefreshToken());
        httpResponse.addCookie(newRefreshTokenCookie);
        return jwtResponse;
    }

    private Cookie createRefreshTokenCookie(String refreshToken) {
        return CookieUtils.createHttpOnlyCookie(
                REFRESH_TOKEN_COOKIE_NAME, refreshToken, "/", refreshTokenAge);
    }

    @GetMapping("/logout")
    public void logout(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        var refreshTokenCookie = CookieUtils.getCookie(httpRequest.getCookies(), REFRESH_TOKEN_COOKIE_NAME);
        if (refreshTokenCookie == null) {
            throw new AccessException(AccessExceptionCodes.EXPIRED_JWT);
        }
        authService.logout(refreshTokenCookie.getValue());
        CookieUtils.deleteCookie(httpRequest.getCookies(), httpResponse, REFRESH_TOKEN_COOKIE_NAME);
    }

    @GetMapping("/access")
    public JwtResponse getNewAccessToken(HttpServletRequest httpRequest) {
        var refreshTokenCookie = CookieUtils.getCookie(httpRequest.getCookies(), REFRESH_TOKEN_COOKIE_NAME);
        var refreshToken = Optional.ofNullable(refreshTokenCookie)
                .map(Cookie::getValue)
                .orElse(null);
        return authService.getAccessToken(refreshToken);
    }

    @GetMapping("/refresh")
    public JwtResponse refreshAccessToken(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        var refreshTokenCookie = CookieUtils.getCookie(httpRequest.getCookies(), REFRESH_TOKEN_COOKIE_NAME);
        var refreshToken = Optional.ofNullable(refreshTokenCookie)
                .map(Cookie::getValue)
                .orElse(null);
        var jwtResponse = authService.refresh(refreshToken);
        var newRefreshTokenCookie = createRefreshTokenCookie(jwtResponse.getRefreshToken());
        httpResponse.addCookie(newRefreshTokenCookie);
        return jwtResponse;
    }

    @PostMapping("/internal/credentials")
    @PreAuthorize("@accessService.checkTechnicalEndpoint(#apiKey)")
    public void updateCredentials(@RequestBody UserCredentialsDto credentials,
                                  @RequestHeader(value = X_API_KEY, required = false) String apiKey) {
        userAuthService.createAuth(credentials);
    }
}
