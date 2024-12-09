package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
