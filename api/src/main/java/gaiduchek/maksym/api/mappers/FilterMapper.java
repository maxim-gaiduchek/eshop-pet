package gaiduchek.maksym.api.mappers;

import gaiduchek.maksym.api.dto.products.FilterDto;
import gaiduchek.maksym.api.model.Filter;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface FilterMapper {

    @Mapping(target = "filterCategoryId", source = "filterCategory.id")
    FilterDto toDto(Filter filter);

    List<FilterDto> toDtos(List<Filter> filters);

    Filter toEntity(FilterDto filterDto);
}
