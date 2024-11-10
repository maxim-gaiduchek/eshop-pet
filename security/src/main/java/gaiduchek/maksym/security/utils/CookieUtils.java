package gaiduchek.maksym.security.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;
import org.springframework.http.ResponseCookie;

import java.util.Arrays;
import java.util.Objects;

@UtilityClass
public class CookieUtils {

    public Cookie getCookie(Cookie[] cookies, String name) {
        if (Objects.isNull(cookies)) return null;
        return Arrays.stream(cookies)
                .filter(cookie -> cookie.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void deleteCookie(Cookie[] cookies, HttpServletResponse httpResponse, String name) {
        if (Objects.isNull(cookies)) return;
        Cookie cookie = getCookie(cookies, name);
        if (Objects.isNull(cookie)) return;
        cookie.setValue(null);
        cookie.setMaxAge(0);
        httpResponse.addCookie(cookie);
    }

    public ResponseCookie createCookie(String name, String value, String path, int maxAge) {
        return ResponseCookie.from(name, value)
                .httpOnly(true)
                .secure(true)
                .path(path)
                .maxAge(maxAge)
                .sameSite("None")
                .build();
    }
}
