package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.search.SearchSellerDto;
import gaiduchek.maksym.api.dto.users.SellerDto;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.UserExceptionCodes;
import gaiduchek.maksym.api.filters.SellerFilter;
import gaiduchek.maksym.api.mappers.SellerMapper;
import gaiduchek.maksym.api.model.Role;
import gaiduchek.maksym.api.model.Seller;
import gaiduchek.maksym.api.repository.SellerRepository;
import gaiduchek.maksym.api.security.services.interfaces.AuthService;
import gaiduchek.maksym.api.services.interfaces.SellerService;
import gaiduchek.maksym.api.services.interfaces.UserService;
import gaiduchek.maksym.api.utils.HashUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final AuthService authService;
    private final UserService userService;

    @Override
    public Optional<Seller> findById(Long id) {
        return sellerRepository.findById(id);
    }

    @Override
    public Seller getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserExceptionCodes.USER_WITH_ID_DOES_NOT_EXIST, id));
    }

    @Override
    public SearchSellerDto find(SellerFilter filter) {
        if (filter == null) {
            return null;
        }
        var specification = filter.buildSpecification();
        var pageable = filter.buildPageable();
        var sellers = sellerRepository.findAll(specification, pageable);
        var sellerDtos = sellerMapper.toDtos(sellers.getContent());
        return SearchSellerDto.builder()
                .sellers(sellerDtos)
                .currentPage(pageable.getPageNumber() + 1)
                .totalPages(sellers.getTotalPages())
                .totalMatches(sellers.getTotalElements())
                .build();
    }

    @Override
    @Transactional
    public Seller create(SellerDto sellerDto) {
        checkCreationPossibility(sellerDto);
        var seller = sellerMapper.toEntity(sellerDto);
        seller.setRole(Role.ROLE_SELLER);
        var savedSeller = sellerRepository.save(seller);
        authService.createCredentials(savedSeller.getId(), sellerDto.getPassword());
        return savedSeller;
    }

    private void checkCreationPossibility(SellerDto sellerDto) {
        if (HashUtils.isEmptySha256Hash(sellerDto.getPassword())) {
            throw new ValidationException(UserExceptionCodes.USER_PASSWORD_IS_EMPTY);
        }
        if (userService.existsByEmail(sellerDto.getEmail())) {
            throw new ValidationException(UserExceptionCodes.USER_WITH_EMAIL_ALREADY_EXISTS, sellerDto.getEmail());
        }
    }

    @Override
    public Seller update(Long id, SellerDto sellerDto) {
        checkUpdatePossibility(id, sellerDto);
        var seller = getByIdOrThrow(id);
        seller.setName(sellerDto.getName());
        seller.setSurname(sellerDto.getSurname());
        seller.setEmail(sellerDto.getEmail());
        seller.setPhone(sellerDto.getPhone());
        seller.setAddress(sellerDto.getAddress());
        return sellerRepository.save(seller);
    }

    private void checkUpdatePossibility(Long id, SellerDto sellerDto) {
        if (userService.existsByEmailAndIdNot(sellerDto.getEmail(), id)) {
            throw new ValidationException(UserExceptionCodes.USER_WITH_EMAIL_ALREADY_EXISTS, sellerDto.getEmail());
        }
    }
}
