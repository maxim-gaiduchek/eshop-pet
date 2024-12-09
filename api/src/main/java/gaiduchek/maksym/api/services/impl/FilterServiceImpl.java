package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.products.FilterDto;
import gaiduchek.maksym.api.exceptions.AccessException;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.AccessExceptionCodes;
import gaiduchek.maksym.api.exceptions.exceptioncodes.FilterExceptionCodes;
import gaiduchek.maksym.api.mappers.FilterMapper;
import gaiduchek.maksym.api.model.Filter;
import gaiduchek.maksym.api.repository.FilterRepository;
import gaiduchek.maksym.api.security.services.interfaces.SecurityProvider;
import gaiduchek.maksym.api.services.interfaces.AdministratorService;
import gaiduchek.maksym.api.services.interfaces.FilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FilterServiceImpl implements FilterService {

    private final FilterRepository filterRepository;
    private final FilterMapper filterMapper;
    private final SecurityProvider securityProvider;
    private final AdministratorService administratorService;

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
        enrichWithResponsible(filter);
        return filterRepository.save(filter);
    }

    private void enrichWithResponsible(Filter filter) {
        var userId = securityProvider.fetchUserId();
        var admin = administratorService.findById(userId)
                .orElseThrow(() -> new AccessException(AccessExceptionCodes.ACCESS_DENIED));
        filter.setResponsible(admin);
    }

    private void checkCreationPossibility(FilterDto filterDto) {
        var name = filterDto.getName();
        if (filterRepository.existsByName(name)) {
            throw new ValidationException(FilterExceptionCodes.FILTER_NAME_ALREADY_EXISTS, name);
        }
    }

    @Override
    public Filter update(Long id, FilterDto filterDto) {
        checkUpdatePossibility(filterDto, id);
        var filter = getByIdOrThrow(id);
        filter.setName(filterDto.getName());
        return filterRepository.save(filter);
    }

    private void checkUpdatePossibility(FilterDto filterDto, Long id) {
        var name = filterDto.getName();
        if (filterRepository.existsByNameAndIdNot(name, id)) {
            throw new ValidationException(FilterExceptionCodes.FILTER_NAME_ALREADY_EXISTS, name);
        }
    }

    @Override
    public void delete(Long id) {
        var filter = getByIdOrThrow(id);
        filter.setDeleted(true);
        filterRepository.save(filter);
    }
}
