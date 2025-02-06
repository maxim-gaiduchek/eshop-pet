package gaiduchek.maksym.api.mappers;

import gaiduchek.maksym.api.dto.products.ProductDto;
import gaiduchek.maksym.api.dto.search.SearchProductQueryDto;
import gaiduchek.maksym.api.filters.ProductFilter;
import gaiduchek.maksym.api.model.Product;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true),
        uses = {FilterMapper.class})
public interface ProductMapper {

    ProductDto toDto(Product product);

    List<ProductDto> toDtos(List<Product> products);

    @Mapping(target = "filters", ignore = true)
    Product toEntity(ProductDto productDto);

    ProductFilter queryToFilter(SearchProductQueryDto queryDto);
}
