package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Long> {
}
