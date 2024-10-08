package gaiduchek.maksym.api.controllers.users;

import gaiduchek.maksym.api.dto.CustomerDto;
import gaiduchek.maksym.api.mappers.CustomerMapper;
import gaiduchek.maksym.api.services.interfaces.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @GetMapping("/{id}")
    // only this user or manager or admin
    public CustomerDto get(@PathVariable Long id) {
        var customer = customerService.getByIdOrThrow(id);
        return customerMapper.toDto(customer);
    }

    @PostMapping
    // only manager
    public CustomerDto create(@RequestBody @Valid CustomerDto customerDto) {
        var customer = customerService.create(customerDto);
        return customerMapper.toDto(customer);
    }

    @PutMapping("/{id}")
    // only this user or manager or admin
    public CustomerDto update(@PathVariable Long id,
                              @RequestBody @Valid CustomerDto customerDto) {
        var customer = customerService.update(id, customerDto);
        return customerMapper.toDto(customer);
    }
}
