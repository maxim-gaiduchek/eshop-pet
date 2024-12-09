package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.dto.products.FilterCategoryDto;
import gaiduchek.maksym.api.model.FilterCategory;

import java.util.Optional;

public interface FilterCategoryService {

    Optional<FilterCategory> findById(Long id);

    FilterCategory getByIdOrThrow(Long id);

    FilterCategory create(FilterCategoryDto filterCategoryDto);

    FilterCategory update(Long id, FilterCategoryDto filterCategoryDto);

    void delete(Long id);
}
