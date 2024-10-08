package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Role;
import gaiduchek.maksym.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByIdAndRole(Long id, Role role);

    Optional<User> findByEmail(String email);
}
