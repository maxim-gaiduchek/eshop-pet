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
        var role = claims.get(JwtClaimsConstants.USER_ROLE_KEY, Role.class);
        jwtAuthentication.setRole(role);
        jwtAuthentication.setLogin(claims.getSubject());
        return jwtAuthentication;
    }
}
