package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.products.FilterCategoryDto;
import gaiduchek.maksym.api.dto.products.FilterDto;
import gaiduchek.maksym.api.exceptions.AccessException;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.AccessExceptionCodes;
import gaiduchek.maksym.api.exceptions.exceptioncodes.FilterExceptionCodes;
import gaiduchek.maksym.api.mappers.FilterCategoryMapper;
import gaiduchek.maksym.api.mappers.FilterMapper;
import gaiduchek.maksym.api.model.Filter;
import gaiduchek.maksym.api.projections.FilterProjection;
import gaiduchek.maksym.api.repository.FilterRepository;
import gaiduchek.maksym.api.security.services.interfaces.SecurityProvider;
import gaiduchek.maksym.api.services.interfaces.AdministratorService;
import gaiduchek.maksym.api.services.interfaces.FilterCategoryService;
import gaiduchek.maksym.api.services.interfaces.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final FilterRepository filterRepository;
    private final FilterMapper filterMapper;
    private final SecurityProvider securityProvider;
    private final AdministratorService administratorService;
    private final FilterCategoryService filterCategoryService;
    private final FilterCategoryMapper filterCategoryMapper;

    @Override
    public Optional<Filter> findById(Long id) {
        return filterRepository.findById(id);
    }

    @Override
    public Filter getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(FilterExceptionCodes.FILTER_DOES_NOT_EXIST, id));
    }

    @Override
    public Filter create(FilterDto filterDto) {
        checkCreationPossibility(filterDto);
        var filter = filterMapper.toEntity(filterDto);
        filter.setDeleted(false);
        filter.setExclude(false);
        enrichWithResponsible(filter);
        enrichWithCategory(filter, filterDto.getFilterCategoryId());
        return filterRepository.save(filter);
    }

    private void checkCreationPossibility(FilterDto filterDto) {
        var name = filterDto.getName();
        var filterCategoryId = filterDto.getFilterCategoryId();
        if (filterRepository.existsByNameAndFilterCategoryIdNot(name, filterCategoryId)) {
            throw new ValidationException(FilterExceptionCodes.FILTER_NAME_ALREADY_EXISTS, name, filterCategoryId);
        }
    }

    private void enrichWithResponsible(Filter filter) {
        var userId = securityProvider.fetchUserId();
        var admin = administratorService.findById(userId)
                .orElseThrow(() -> new AccessException(AccessExceptionCodes.ACCESS_DENIED));
        filter.setResponsible(admin);
    }

    private void enrichWithCategory(Filter filter, Long filterCategoryId) {
        var filterCategory = filterCategoryService.getByIdOrThrow(filterCategoryId);
        filter.setFilterCategory(filterCategory);
    }

    @Override
    public Filter update(Long id, FilterDto filterDto) {
        checkUpdatePossibility(filterDto, id);
        var filter = getByIdOrThrow(id);
        filter.setName(filterDto.getName());
        enrichWithCategory(filter, filterDto.getFilterCategoryId());
        return filterRepository.save(filter);
    }

    private void checkUpdatePossibility(FilterDto filterDto, Long id) {
        var name = filterDto.getName();
        var filterCategoryId = filterDto.getFilterCategoryId();
        if (filterRepository.existsByNameAndIdNotAndFilterCategoryIdNot(name, id, filterCategoryId)) {
            throw new ValidationException(FilterExceptionCodes.FILTER_NAME_ALREADY_EXISTS, name, filterCategoryId);
        }
    }

    @Override
    public void delete(Long id) {
        var filter = getByIdOrThrow(id);
        filter.setDeleted(true);
        filterRepository.save(filter);
    }

    @Override
    public List<FilterCategoryDto> getAll(List<Long> selectedFilterIds, List<Long> excludedFilterCategoriesIds) {
        return filterRepository.getAllWithCounts(selectedFilterIds).stream()
                .collect(Collectors.groupingBy(FilterProjection::getFilterCategory))
                .entrySet()
                .stream()
                .map(entry -> filterCategoryMapper.toDto(entry.getKey(), entry.getValue()))
                .toList();
    }
}
