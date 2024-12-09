package gaiduchek.maksym.api.controllers.products;

import gaiduchek.maksym.api.dto.products.FilterCategoryDto;
import gaiduchek.maksym.api.mappers.FilterCategoryMapper;
import gaiduchek.maksym.api.services.interfaces.FilterCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/filters-categories")
@RequiredArgsConstructor
public class FilterCategoryController {

    private final FilterCategoryService filterCategoryService;
    private final FilterCategoryMapper filterCategoryMapper;

    @PostMapping
    @PreAuthorize("@accessService.isAdministrator()")
    public FilterCategoryDto create(@RequestBody @Valid FilterCategoryDto filterCategoryDto) {
        var filterCategory = filterCategoryService.create(filterCategoryDto);
        return filterCategoryMapper.toDto(filterCategory);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@accessService.isAdministrator()")
    public FilterCategoryDto update(@PathVariable Long id,
                                    @RequestBody @Valid FilterCategoryDto filterCategoryDto) {
        var filterCategory = filterCategoryService.update(id, filterCategoryDto);
        return filterCategoryMapper.toDto(filterCategory);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@accessService.isAdministrator()")
    public void delete(@PathVariable Long id) {
        filterCategoryService.delete(id);
    }
}