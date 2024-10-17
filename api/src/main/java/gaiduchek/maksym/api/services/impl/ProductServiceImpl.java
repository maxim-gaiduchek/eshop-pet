package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.products.ProductDto;
import gaiduchek.maksym.api.dto.search.SearchProductDto;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.ProductExceptionCodes;
import gaiduchek.maksym.api.filters.ProductFilter;
import gaiduchek.maksym.api.mappers.ProductMapper;
import gaiduchek.maksym.api.model.Company;
import gaiduchek.maksym.api.model.Product;
import gaiduchek.maksym.api.repository.ProductRepository;
import gaiduchek.maksym.api.security.services.interfaces.AccessService;
import gaiduchek.maksym.api.security.services.interfaces.SecurityProvider;
import gaiduchek.maksym.api.services.interfaces.CompanyService;
import gaiduchek.maksym.api.services.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SecurityProvider securityProvider;
    private final CompanyService companyService;
    private final AccessService accessService;

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ProductExceptionCodes.PRODUCT_DOES_NOT_EXIST, id));
    }

    @Override
    public SearchProductDto find(ProductFilter filter) {
        if (filter == null) {
            return null;
        }
        var specification = filter.buildSpecification();
        var pageable = filter.buildPageable();
        var products = productRepository.findAll(specification, pageable);
        var productDtos = productMapper.toDtos(products.getContent());
        return SearchProductDto.builder()
                .products(productDtos)
                .currentPage(pageable.getPageNumber() + 1)
                .totalPages(products.getTotalPages())
                .totalMatches(products.getTotalElements())
                .build();
    }

    @Override
    public Product create(ProductDto productDto) {
        var product = productMapper.toEntity(productDto);
        enrichWithCompany(product, productDto);
        product.setDeleted(false);
        return productRepository.save(product);
    }

    private void enrichWithCompany(Product product, ProductDto productDto) {
        var companyId = productDto.getCompany().getId();
        var company = fetchCompany(companyId);
        product.setCompany(company);
    }

    private Company fetchCompany(Long companyId) {
        var company = companyService.getByIdOrThrow(companyId);
        accessService.checkUserOwnsCompany(company);
        return company;
    }

    @Override
    public Product update(Long id, ProductDto productDto) {
        var product = getByIdOrThrow(id);
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setCost(productDto.getCost());
        product.setCount(productDto.getCount()); // TODO validate if count >= purchasedCount
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        var product = getByIdOrThrow(id);
        product.setDeleted(true);
        productRepository.save(product);
    }
}
