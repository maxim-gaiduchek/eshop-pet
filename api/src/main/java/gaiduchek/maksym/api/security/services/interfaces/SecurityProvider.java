package gaiduchek.maksym.api.security.services.interfaces;

import gaiduchek.maksym.api.model.User;
import gaiduchek.maksym.api.security.model.JwtAuthentication;

public interface SecurityProvider {

    JwtAuthentication fetchAuthentication();

    Long fetchUserId();

    User fetchUser();
}
