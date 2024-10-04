package gaiduchek.maksym.security.services.interfaces;

import gaiduchek.maksym.security.dto.UserDto;

public interface UserService {

    UserDto getByIdOtThrow(Long id);

    UserDto getByEmailOrThrow(String email);
}
