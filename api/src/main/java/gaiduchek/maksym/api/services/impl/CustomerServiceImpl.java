package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.users.CustomerDto;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.UserExceptionCodes;
import gaiduchek.maksym.api.mappers.CustomerMapper;
import gaiduchek.maksym.api.model.Customer;
import gaiduchek.maksym.api.model.Role;
import gaiduchek.maksym.api.repository.CustomerRepository;
import gaiduchek.maksym.api.security.services.interfaces.AuthService;
import gaiduchek.maksym.api.services.interfaces.CustomerService;
import gaiduchek.maksym.api.utils.HashUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AuthService authService;

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
        checkCreationPossibility(customerDto);
        var customer = customerMapper.toEntity(customerDto);
        customer.setRole(Role.ROLE_CUSTOMER);
        authService.createCredentials(customer.getId(), customerDto.getPassword());
        return customerRepository.save(customer);
    }

    private void checkCreationPossibility(CustomerDto customerDto) {
        if (HashUtils.isEmptySha256Hash(customerDto.getPassword())) {
            throw new ValidationException(UserExceptionCodes.USER_PASSWORD_IS_EMPTY);
        }
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
