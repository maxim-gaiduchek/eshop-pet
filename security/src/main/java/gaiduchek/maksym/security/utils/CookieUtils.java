package gaiduchek.maksym.security.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.experimental.UtilityClass;

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

    public Cookie createHttpOnlyCookie(String name, String value, String path, int maxAge) {
        var cookie = new Cookie(name, value);
        cookie.setMaxAge(maxAge);
        cookie.setPath(path);
        cookie.setSecure(true);
        cookie.setHttpOnly(true);
        cookie.setAttribute("SameSite", "None");
        return cookie;
    }
}
