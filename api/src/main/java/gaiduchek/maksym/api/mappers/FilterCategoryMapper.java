package gaiduchek.maksym.api.mappers;

import gaiduchek.maksym.api.dto.products.FilterCategoryDto;
import gaiduchek.maksym.api.model.FilterCategory;
import gaiduchek.maksym.api.projections.FilterProjection;
import org.mapstruct.Builder;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true),
        uses = {FilterMapper.class})
public interface FilterCategoryMapper {

    @Named("toFilterCategoryDto")
    FilterCategoryDto toDto(FilterCategory filterCategory);

    @Named("toFilterCategoryWithResponsibleDto")
    FilterCategoryDto toDtoWithResponsible(FilterCategory filterCategory);

    FilterCategoryDto toDto(Long id, String name, List<FilterProjection> filters);

    @IterableMapping(qualifiedByName = "toFilterCategoryDto")
    List<FilterCategoryDto> toDtos(List<FilterCategory> filterCategories);

    FilterCategory toEntity(FilterCategoryDto filterCategoryDto);
}
