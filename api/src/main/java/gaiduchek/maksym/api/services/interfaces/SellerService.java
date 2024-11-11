package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.dto.search.SearchSellerDto;
import gaiduchek.maksym.api.dto.users.SellerDto;
import gaiduchek.maksym.api.filters.SellerFilter;
import gaiduchek.maksym.api.model.Seller;

import java.util.Optional;

public interface SellerService {

    Optional<Seller> findById(Long id);

    Seller getByIdOrThrow(Long id);

    Seller create(SellerDto sellerDto);

    Seller update(Long id, SellerDto sellerDto);

    SearchSellerDto find(SellerFilter filter);
}
