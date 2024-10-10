package gaiduchek.maksym.api.security.services.impl;

import gaiduchek.maksym.api.clients.SecurityClient;
import gaiduchek.maksym.api.dto.users.UserCredentialsDto;
import gaiduchek.maksym.api.security.services.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final SecurityClient securityClient;

    @Override
    public void createCredentials(Long userId, String password) {
        var userCredentialsDto = UserCredentialsDto.builder()
                .userId(userId)
                .password(password)
                .build();
        securityClient.createCredentials(userCredentialsDto);
    }
}
