package gaiduchek.maksym.api.repository;

import gaiduchek.maksym.api.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {
}
