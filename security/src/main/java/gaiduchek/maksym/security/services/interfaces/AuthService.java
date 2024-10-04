package gaiduchek.maksym.security.services.interfaces;

import gaiduchek.maksym.security.dto.JwtRequest;
import gaiduchek.maksym.security.dto.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest authRequest);

    void logout(String refreshToken);

    JwtResponse getAccessToken(String refreshToken);

    JwtResponse refresh(String refreshToken);
}
