package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.products.CompanyDto;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.CompanyExceptionCodes;
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
    public Company create(CompanyDto companyDto) {
        var company = companyMapper.toEntity(companyDto);
        enrichWithSeller(company);
        company.setDeleted(false);
        return companyRepository.save(company);
    }

    private void enrichWithSeller(Company company) {
        var userId = securityProvider.fetchUserId();
        var seller = sellerService.getByIdOrThrow(userId);
        company.setSeller(seller);
    }

    @Override
    public Company update(Long id, CompanyDto companyDto) {
        var company = fetchCompany(id);
        company.setName(companyDto.getName());
        return companyRepository.save(company);
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
