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
import gaiduchek.maksym.api.services.interfaces.UserService;
import gaiduchek.maksym.api.utils.HashUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final AuthService authService;
    private final UserService userService;

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
    @Transactional
    public Customer create(CustomerDto customerDto) {
        checkCreationPossibility(customerDto);
        var customer = customerMapper.toEntity(customerDto);
        customer.setRole(Role.ROLE_CUSTOMER);
        var savedCustomer = customerRepository.save(customer);
        authService.createCredentials(savedCustomer.getId(), customerDto.getPassword());
        return savedCustomer;
    }

    private void checkCreationPossibility(CustomerDto customerDto) {
        if (HashUtils.isEmptySha256Hash(customerDto.getPassword())) {
            throw new ValidationException(UserExceptionCodes.USER_PASSWORD_IS_EMPTY);
        }
        if (userService.existsByEmail(customerDto.getEmail())) {
            throw new ValidationException(UserExceptionCodes.USER_WITH_EMAIL_ALREADY_EXISTS, customerDto.getEmail());
        }
    }

    @Override
    public Customer update(Long id, CustomerDto customerDto) {
        checkUpdatePossibility(id, customerDto);
        var customer = getByIdOrThrow(id);
        customer.setName(customerDto.getName());
        customer.setSurname(customerDto.getSurname());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setAddress(customerDto.getAddress());
        return customerRepository.save(customer);
    }

    private void checkUpdatePossibility(Long id, CustomerDto customerDto) {
        if (userService.existsByEmailAndIdNot(customerDto.getEmail(), id)) {
            throw new ValidationException(UserExceptionCodes.USER_WITH_EMAIL_ALREADY_EXISTS, customerDto.getEmail());
        }
    }
}
