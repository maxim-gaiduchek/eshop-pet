package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.dto.users.CustomerDto;
import gaiduchek.maksym.api.model.Customer;

import java.util.Optional;

public interface CustomerService {

    Optional<Customer> findById(Long id);

    Customer getByIdOrThrow(Long id);

    Customer create(CustomerDto customerDto);

    Customer update(Long id, CustomerDto customerDto);
}
