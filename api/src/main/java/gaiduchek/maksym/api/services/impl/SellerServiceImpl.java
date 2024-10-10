package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.users.SellerDto;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.UserExceptionCodes;
import gaiduchek.maksym.api.mappers.SellerMapper;
import gaiduchek.maksym.api.model.Role;
import gaiduchek.maksym.api.model.Seller;
import gaiduchek.maksym.api.repository.SellerRepository;
import gaiduchek.maksym.api.security.services.interfaces.AuthService;
import gaiduchek.maksym.api.services.interfaces.SellerService;
import gaiduchek.maksym.api.utils.HashUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final SellerMapper sellerMapper;
    private final AuthService authService;

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
    public Seller create(SellerDto sellerDto) {
        checkCreationPossibility(sellerDto);
        var seller = sellerMapper.toEntity(sellerDto);
        seller.setRole(Role.ROLE_SELLER);
        authService.createCredentials(seller.getId(), sellerDto.getPassword());
        return sellerRepository.save(seller);
    }

    private void checkCreationPossibility(SellerDto sellerDto) {
        if (HashUtils.isEmptySha256Hash(sellerDto.getPassword())) {
            throw new ValidationException(UserExceptionCodes.USER_PASSWORD_IS_EMPTY);
        }
    }

    @Override
    public Seller update(Long id, SellerDto sellerDto) {
        var seller = getByIdOrThrow(id);
        seller.setName(sellerDto.getName());
        seller.setSurname(sellerDto.getSurname());
        seller.setEmail(sellerDto.getEmail());
        seller.setPhone(sellerDto.getPhone());
        seller.setAddress(sellerDto.getAddress());
        return sellerRepository.save(seller);
    }
}
