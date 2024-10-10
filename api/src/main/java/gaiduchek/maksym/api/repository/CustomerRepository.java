package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
