package gaiduchek.maksym.security.services.impl;

import gaiduchek.maksym.security.exceptions.EntityNotFoundException;
import gaiduchek.maksym.security.exceptions.exceptioncodes.UserAuthExceptionCodes;
import gaiduchek.maksym.security.model.UserAuth;
import gaiduchek.maksym.security.repositories.UserAuthRepository;
import gaiduchek.maksym.security.services.interfaces.UserAuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepository userAuthRepository;

    @Override
    public UserAuth getByUserIdOrThrow(Long userId) {
        return userAuthRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(UserAuthExceptionCodes.USER_AUTH_DOES_NOT_EXIST));
    }

    @Override
    public UserAuth getByRefreshTokenOrThrow(String refreshToken) {
        return userAuthRepository.findByRefreshTokensContains(refreshToken)
                .orElseThrow(() -> new EntityNotFoundException(UserAuthExceptionCodes.USER_AUTH_DOES_NOT_EXIST));
    }

    @Override
    public UserAuth save(UserAuth userAuth) {
        return userAuthRepository.save(userAuth);
    }
}
