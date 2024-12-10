package gaiduchek.maksym.api.controllers.products;

import gaiduchek.maksym.api.dto.products.FilterCategoryDto;
import gaiduchek.maksym.api.dto.products.FilterDto;
import gaiduchek.maksym.api.mappers.FilterMapper;
import gaiduchek.maksym.api.services.interfaces.FilterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/filters")
@RequiredArgsConstructor
public class FilterController {

    private final FilterService filterService;
    private final FilterMapper filterMapper;

    @PostMapping
    @PreAuthorize("@accessService.isAdministrator()")
    public FilterDto create(@RequestBody @Valid FilterDto filterDto) {
        var filter = filterService.create(filterDto);
        return filterMapper.toDto(filter);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@accessService.isAdministrator()")
    public FilterDto update(@PathVariable Long id,
                            @RequestBody @Valid FilterDto filterDto) {
        var filter = filterService.update(id, filterDto);
        return filterMapper.toDto(filter);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("@accessService.isAdministrator()")
    public void delete(@PathVariable Long id) {
        filterService.delete(id);
    }

    @GetMapping
    public List<FilterCategoryDto> getAll(@RequestParam(required = false) List<Long> selectedFilterIds) {
        return filterService.getAll(selectedFilterIds);
    }
}
