package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.products.FilterCategoryDto;
import gaiduchek.maksym.api.exceptions.AccessException;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.AccessExceptionCodes;
import gaiduchek.maksym.api.exceptions.exceptioncodes.FilterCategoryExceptionCodes;
import gaiduchek.maksym.api.mappers.FilterCategoryMapper;
import gaiduchek.maksym.api.model.FilterCategory;
import gaiduchek.maksym.api.repository.FilterCategoryRepository;
import gaiduchek.maksym.api.security.services.interfaces.SecurityProvider;
import gaiduchek.maksym.api.services.interfaces.AdministratorService;
import gaiduchek.maksym.api.services.interfaces.FilterCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilterCategoryServiceImpl implements FilterCategoryService {

    private final FilterCategoryRepository filterCategoryRepository;
    private final FilterCategoryMapper filterCategoryMapper;
    private final SecurityProvider securityProvider;
    private final AdministratorService administratorService;

    @Override
    public Optional<FilterCategory> findById(Long id) {
        return filterCategoryRepository.findById(id);
    }

    @Override
    public FilterCategory getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(FilterCategoryExceptionCodes.FILTER_CATEGORY_DOES_NOT_EXIST, id));
    }

    @Override
    public List<FilterCategory> findAll() {
        return filterCategoryRepository.findAll();
    }

    @Override
    public FilterCategory create(FilterCategoryDto filterDto) {
        checkCreationPossibility(filterDto);
        var filterCategory = filterCategoryMapper.toEntity(filterDto);
        filterCategory.setDeleted(false);
        enrichWithResponsible(filterCategory);
        return filterCategoryRepository.save(filterCategory);
    }

    private void enrichWithResponsible(FilterCategory filterCategory) {
        var userId = securityProvider.fetchUserId();
        var admin = administratorService.findById(userId)
                .orElseThrow(() -> new AccessException(AccessExceptionCodes.ACCESS_DENIED));
        filterCategory.setResponsible(admin);
    }

    private void checkCreationPossibility(FilterCategoryDto filterDto) {
        var name = filterDto.getName();
        if (filterCategoryRepository.existsByName(name)) {
            throw new ValidationException(FilterCategoryExceptionCodes.FILTER_CATEGORY_NAME_ALREADY_EXISTS, name);
        }
    }

    @Override
    public FilterCategory update(Long id, FilterCategoryDto filterDto) {
        checkUpdatePossibility(filterDto, id);
        var filterCategory = getByIdOrThrow(id);
        filterCategory.setName(filterDto.getName());
        return filterCategoryRepository.save(filterCategory);
    }

    private void checkUpdatePossibility(FilterCategoryDto filterDto, Long id) {
        var name = filterDto.getName();
        if (filterCategoryRepository.existsByNameAndIdNot(name, id)) {
            throw new ValidationException(FilterCategoryExceptionCodes.FILTER_CATEGORY_NAME_ALREADY_EXISTS, name);
        }
    }

    @Override
    public void delete(Long id) {
        var filterCategory = getByIdOrThrow(id);
        filterCategory.setDeleted(true);
        filterCategory.getFilters().forEach(filter -> filter.setDeleted(true));
        filterCategoryRepository.save(filterCategory);
    }
}
