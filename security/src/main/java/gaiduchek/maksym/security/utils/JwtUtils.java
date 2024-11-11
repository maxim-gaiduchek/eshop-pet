package gaiduchek.maksym.security.utils;

import gaiduchek.maksym.security.constants.JwtClaimsConstants;
import gaiduchek.maksym.security.model.JwtAuthentication;
import gaiduchek.maksym.security.model.Role;
import io.jsonwebtoken.Claims;
import lombok.experimental.UtilityClass;

@UtilityClass
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        var jwtAuthentication = new JwtAuthentication();
        var userId = claims.get(JwtClaimsConstants.USER_ID_KEY, Long.class);
        var role = fetchRole(claims);
        jwtAuthentication.setUserId(userId);
        jwtAuthentication.setRole(role);
        jwtAuthentication.setLogin(claims.getSubject());
        return jwtAuthentication;
    }

    private Role fetchRole(Claims claims) {
        var role = claims.get(JwtClaimsConstants.USER_ROLE_KEY, String.class);
        return Role.valueOf(role);
    }
}
