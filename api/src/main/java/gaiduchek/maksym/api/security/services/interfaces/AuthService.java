package gaiduchek.maksym.api.security.services.interfaces;

public interface AuthService {

    void createCredentials(Long userId, String password);
}
