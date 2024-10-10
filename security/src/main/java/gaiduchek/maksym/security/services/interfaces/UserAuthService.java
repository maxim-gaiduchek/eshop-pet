package gaiduchek.maksym.security.services.interfaces;

import gaiduchek.maksym.security.dto.UserCredentialsDto;
import gaiduchek.maksym.security.model.UserAuth;

public interface UserAuthService {

    UserAuth getByUserIdOrThrow(Long userId);

    UserAuth getByRefreshTokenOrThrow(String refreshToken);

    UserAuth save(UserAuth userAuth);

    void createAuth(UserCredentialsDto credentials);
}
