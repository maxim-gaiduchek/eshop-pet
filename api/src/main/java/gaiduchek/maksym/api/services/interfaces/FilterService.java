package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.dto.products.FilterCategoryDto;
import gaiduchek.maksym.api.dto.products.FilterDto;
import gaiduchek.maksym.api.model.Filter;

import java.util.List;
import java.util.Optional;

public interface FilterService {

    Optional<Filter> findById(Long id);

    Filter getByIdOrThrow(Long id);

    Filter create(FilterDto filterDto);

    Filter update(Long id, FilterDto filterDto);

    void delete(Long id);

    List<FilterCategoryDto> getAll(List<Long> selectedFilterIds, List<Long> excludedFilterCategoriesIds);
}
