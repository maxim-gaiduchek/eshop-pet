package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.dto.products.CompanyDto;
import gaiduchek.maksym.api.dto.search.SearchCompanyDto;
import gaiduchek.maksym.api.filters.CompanyFilter;
import gaiduchek.maksym.api.model.Company;

import java.util.Optional;

public interface CompanyService {

    Optional<Company> findById(Long id);

    Company getByIdOrThrow(Long id);

    SearchCompanyDto find(CompanyFilter filter);

    Company create(CompanyDto companyDto);

    Company update(Long id, CompanyDto companyDto);

    void delete(Long id);
}
