package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.CustomerDto;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.UserExceptionCodes;
import gaiduchek.maksym.api.mappers.CustomerMapper;
import gaiduchek.maksym.api.model.Customer;
import gaiduchek.maksym.api.repository.CustomerRepository;
import gaiduchek.maksym.api.services.interfaces.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
    }

    @Override
    public Customer getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserExceptionCodes.USER_WITH_ID_DOES_NOT_EXIST, id));
    }

    @Override
    public Customer create(CustomerDto customerDto) {
        var customer = customerMapper.toEntity(customerDto);
        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Long id, CustomerDto customerDto) {
        var customer = getByIdOrThrow(id);
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());
        return customerRepository.save(customer);
    }
}
