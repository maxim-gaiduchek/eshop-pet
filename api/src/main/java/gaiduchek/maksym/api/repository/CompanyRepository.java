package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
