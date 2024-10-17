package gaiduchek.maksym.api.controllers.products;

import gaiduchek.maksym.api.dto.products.CompanyDto;
import gaiduchek.maksym.api.mappers.CompanyMapper;
import gaiduchek.maksym.api.services.interfaces.CompanyService;
import gaiduchek.maksym.api.validation.groups.CreateGroup;
import gaiduchek.maksym.api.validation.groups.UpdateGroup;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyMapper companyMapper;

    @GetMapping("/{id}")
    public CompanyDto get(@PathVariable Long id) {
        var company = companyService.getByIdOrThrow(id);
        return companyMapper.toDto(company);
    }

    @PostMapping
    @RolesAllowed("ROLE_SELLER")
    public CompanyDto create(@RequestBody @Validated(CreateGroup.class) CompanyDto companyDto) {
        var company = companyService.create(companyDto);
        return companyMapper.toDto(company);
    }

    @PutMapping("/{id}")
    @RolesAllowed("ROLE_SELLER")
    public CompanyDto update(@PathVariable Long id,
                             @RequestBody @Validated(UpdateGroup.class) CompanyDto companyDto) {
        var company = companyService.update(id, companyDto);
        return companyMapper.toDto(company);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_SELLER", "ROLE_ADMINISTRATOR"})
    public void delete(@PathVariable Long id) {
        companyService.delete(id);
    }
}
