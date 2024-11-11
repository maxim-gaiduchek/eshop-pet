package gaiduchek.maksym.api.security.services.impl;

import gaiduchek.maksym.api.model.User;
import gaiduchek.maksym.api.security.model.JwtAuthentication;
import gaiduchek.maksym.api.security.services.interfaces.SecurityProvider;
import gaiduchek.maksym.api.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Profile("!local")
public class SecurityProviderImpl implements SecurityProvider {

    private final UserService userService;

    @Override
    public JwtAuthentication fetchAuthentication() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public Long fetchUserId() {
        return Optional.ofNullable(fetchAuthentication())
                .map(JwtAuthentication::getUserId)
                .orElse(null);
    }

    @Override
    public User fetchUser() {
        return Optional.ofNullable(fetchUserId())
                .map(userService::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .orElse(null);
    }
}
