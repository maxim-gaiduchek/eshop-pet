package gaiduchek.maksym.security.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    ROLE_CUSTOMER("ROLE_CUSTOMER"),
    ROLE_SELLER("ROLE_SELLER"),
    ROLE_MANAGER("ROLE_MANAGER"),
    ROLE_ADMINISTRATOR("ROLE_ADMINISTRATOR"),
    ;

    private final String value;

    @Override
    public String getAuthority() {
        return value;
    }
}
