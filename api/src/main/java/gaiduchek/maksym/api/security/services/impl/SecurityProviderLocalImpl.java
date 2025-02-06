package gaiduchek.maksym.api.security.services.impl;

import gaiduchek.maksym.api.model.Role;
import gaiduchek.maksym.api.model.User;
import gaiduchek.maksym.api.security.model.JwtAuthentication;
import gaiduchek.maksym.api.security.services.interfaces.SecurityProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Profile("local")
public class SecurityProviderLocalImpl implements SecurityProvider {

    @Override
    public JwtAuthentication fetchAuthentication() {
        return (JwtAuthentication) SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public Long fetchUserId() {
        return 3L;
    }

    @Override
    public User fetchUser() {
        return User.builder()
                .id(fetchUserId())
                .name("Test")
                .surname("Admin")
                .email("test@gmail.com")
                .phone("+420607777777")
                .role(Role.ROLE_SELLER)
                .build();
    }
}
