package gaiduchek.maksym.api.mappers;

import gaiduchek.maksym.api.dto.products.FilterCategoryDto;
import gaiduchek.maksym.api.model.FilterCategory;
import gaiduchek.maksym.api.projections.FilterProjection;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true),
        uses = {FilterMapper.class})
public interface FilterCategoryMapper {

    FilterCategoryDto toDto(FilterCategory filterCategory);

    FilterCategoryDto toDto(FilterCategory filterCategory, List<FilterProjection> filters);

    List<FilterCategoryDto> toDtos(List<FilterCategory> filterCategories);

    FilterCategory toEntity(FilterCategoryDto filterCategoryDto);
}
