package gaiduchek.maksym.api.controllers.users;

import gaiduchek.maksym.api.dto.users.CustomerDto;
import gaiduchek.maksym.api.mappers.CustomerMapper;
import gaiduchek.maksym.api.services.interfaces.CustomerService;
import gaiduchek.maksym.api.validation.groups.CreateGroup;
import gaiduchek.maksym.api.validation.groups.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
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
    @PreAuthorize("@accessService.isUserThemself(#id) or @accessService.isWorker()")
    public CustomerDto get(@PathVariable Long id) {
        var customer = customerService.getByIdOrThrow(id);
        return customerMapper.toDto(customer);
    }

    @PostMapping
    // TODO
    public CustomerDto create(@RequestBody @Validated(CreateGroup.class) CustomerDto customerDto) {
        var customer = customerService.create(customerDto);
        return customerMapper.toDto(customer);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@accessService.isUserThemself(#id) or @accessService.isWorker()")
    public CustomerDto update(@PathVariable Long id,
                              @RequestBody @Validated(UpdateGroup.class) CustomerDto customerDto) {
        var customer = customerService.update(id, customerDto);
        return customerMapper.toDto(customer);
    }
}
