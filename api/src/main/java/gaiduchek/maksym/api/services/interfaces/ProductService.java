package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.dto.products.ProductDto;
import gaiduchek.maksym.api.dto.search.SearchProductDto;
import gaiduchek.maksym.api.filters.ProductFilter;
import gaiduchek.maksym.api.model.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface ProductService {

    Optional<Product> findById(Long id);

    Product getByIdOrThrow(Long id);

    SearchProductDto find(ProductFilter filter);

    Product create(ProductDto productDto, MultipartFile productImageFile);

    Product update(Long id, ProductDto productDto, MultipartFile productImageFile);

    void delete(Long id);
}
