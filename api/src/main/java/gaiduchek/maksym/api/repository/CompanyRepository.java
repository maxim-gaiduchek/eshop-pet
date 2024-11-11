package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Company;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

    Page<Company> findAll(Specification<Company> specification, Pageable pageable);
}
