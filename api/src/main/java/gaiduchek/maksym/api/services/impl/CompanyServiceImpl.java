package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.products.CompanyDto;
import gaiduchek.maksym.api.dto.search.SearchCompanyDto;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.CompanyExceptionCodes;
import gaiduchek.maksym.api.filters.CompanyFilter;
import gaiduchek.maksym.api.mappers.CompanyMapper;
import gaiduchek.maksym.api.model.Company;
import gaiduchek.maksym.api.repository.CompanyRepository;
import gaiduchek.maksym.api.security.services.interfaces.AccessService;
import gaiduchek.maksym.api.security.services.interfaces.SecurityProvider;
import gaiduchek.maksym.api.services.interfaces.CompanyService;
import gaiduchek.maksym.api.services.interfaces.SellerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Lazy})
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final SecurityProvider securityProvider;
    private final SellerService sellerService;
    private final AccessService accessService;

    @Override
    public Optional<Company> findById(Long id) {
        return companyRepository.findById(id);
    }

    @Override
    public Company getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(CompanyExceptionCodes.COMPANY_DOES_NOT_EXIST, id));
    }

    @Override
    public SearchCompanyDto find(CompanyFilter filter) {
        if (filter == null) {
            return null;
        }
        var specification = filter.buildSpecification();
        var pageable = filter.buildPageable();
        var companies = companyRepository.findAll(specification, pageable);
        var companyDtos = companyMapper.toDtos(companies.getContent());
        return SearchCompanyDto.builder()
                .companies(companyDtos)
                .currentPage(pageable.getPageNumber() + 1)
                .totalPages(companies.getTotalPages())
                .totalMatches(companies.getTotalElements())
                .build();
    }

    @Override
    public Company create(CompanyDto companyDto) {
        checkCreationPossibility(companyDto);
        var company = companyMapper.toEntity(companyDto);
        enrichWithSeller(company);
        company.setDeleted(false);
        return companyRepository.save(company);
    }

    private void checkCreationPossibility(CompanyDto companyDto) {
        var name = companyDto.getName();
        if (companyRepository.existsByName(name)) {
            throw new ValidationException(CompanyExceptionCodes.COMPANY_NAME_ALREADY_EXISTS, name);
        }
    }

    private void enrichWithSeller(Company company) {
        var userId = securityProvider.fetchUserId();
        var seller = sellerService.getByIdOrThrow(userId);
        company.setSeller(seller);
    }

    @Override
    public Company update(Long id, CompanyDto companyDto) {
        checkUpdatePossibility(companyDto, id);
        var company = fetchCompany(id);
        company.setName(companyDto.getName());
        return companyRepository.save(company);
    }

    private void checkUpdatePossibility(CompanyDto companyDto, Long id) {
        var name = companyDto.getName();
        if (companyRepository.existsByNameAndIdNot(name, id)) {
            throw new ValidationException(CompanyExceptionCodes.COMPANY_NAME_ALREADY_EXISTS, name);
        }
    }

    private Company fetchCompany(Long companyId) {
        var company = getByIdOrThrow(companyId);
        accessService.checkUserOwnsCompany(company);
        return company;
    }

    @Override
    public void delete(Long id) {
        var company = getByIdOrThrow(id);
        CollectionUtils.emptyIfNull(company.getProducts())
                .forEach(product -> product.setDeleted(true));
        company.setDeleted(true);
        companyRepository.save(company);
    }
}
