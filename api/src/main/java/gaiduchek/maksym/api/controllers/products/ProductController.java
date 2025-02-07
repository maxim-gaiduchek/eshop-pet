package gaiduchek.maksym.api.controllers.products;

import gaiduchek.maksym.api.dto.products.ProductDto;
import gaiduchek.maksym.api.dto.search.SearchProductDto;
import gaiduchek.maksym.api.dto.search.SearchProductQueryDto;
import gaiduchek.maksym.api.mappers.ProductMapper;
import gaiduchek.maksym.api.security.services.interfaces.AccessService;
import gaiduchek.maksym.api.services.interfaces.ProductService;
import gaiduchek.maksym.api.validation.groups.CreateGroup;
import gaiduchek.maksym.api.validation.groups.UpdateGroup;
import jakarta.annotation.security.RolesAllowed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;
    private final AccessService accessService;

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable Long id) {
        var product = productService.getByIdOrThrow(id);
        return productMapper.toDto(product);
    }

    @GetMapping
    public SearchProductDto findAll(@ModelAttribute SearchProductQueryDto queryDto) {
        var filter = productMapper.queryToFilter(queryDto);
        filter.setAccessService(accessService);
        return productService.find(filter);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RolesAllowed("ROLE_SELLER")
    public ProductDto create(@RequestPart("product") @Validated(CreateGroup.class) ProductDto productDto,
                             @RequestPart MultipartFile productImageFile) {
        var product = productService.create(productDto, productImageFile);
        return productMapper.toDto(product);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @RolesAllowed("ROLE_SELLER")
    public ProductDto update(@PathVariable Long id,
                             @RequestPart("product") @Validated(UpdateGroup.class) ProductDto productDto,
                             @RequestPart(required = false) MultipartFile productImageFile) {
        var product = productService.update(id, productDto, productImageFile);
        return productMapper.toDto(product);
    }

    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_SELLER", "ROLE_ADMINISTRATOR"})
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }
}
