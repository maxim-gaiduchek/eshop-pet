package gaiduchek.maksym.security.services.impl;

import gaiduchek.maksym.security.clients.ApiClient;
import gaiduchek.maksym.security.dto.UserDto;
import gaiduchek.maksym.security.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ApiClient apiClient;

    @Override
    public UserDto getByIdOtThrow(Long id) {
        return apiClient.getByIdOrThrow(id);
    }

    @Override
    public UserDto getByEmailOrThrow(String email) {
        return apiClient.getByEmailOrThrow(email);
    }
}
