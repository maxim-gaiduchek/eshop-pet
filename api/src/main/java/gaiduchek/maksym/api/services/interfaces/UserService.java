package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findById(Long id);

    User getByIdOrThrow(Long id);

    User getByEmailOrThrow(String email);
}
