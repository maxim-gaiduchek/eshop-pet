package gaiduchek.maksym.security.repositories;

import gaiduchek.maksym.security.model.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    Optional<UserAuth> findByUserId(Long userId);

    Optional<UserAuth> findByRefreshTokensContains(String refreshToken);
}
