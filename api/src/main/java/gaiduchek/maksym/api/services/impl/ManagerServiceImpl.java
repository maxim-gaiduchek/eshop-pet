package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.dto.users.ManagerDto;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.UserExceptionCodes;
import gaiduchek.maksym.api.mappers.ManagerMapper;
import gaiduchek.maksym.api.model.Manager;
import gaiduchek.maksym.api.model.Role;
import gaiduchek.maksym.api.repository.ManagerRepository;
import gaiduchek.maksym.api.security.services.interfaces.AuthService;
import gaiduchek.maksym.api.services.interfaces.ManagerService;
import gaiduchek.maksym.api.utils.HashUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final ManagerMapper managerMapper;
    private final AuthService authService;

    @Override
    public Optional<Manager> findById(Long id) {
        return managerRepository.findById(id);
    }

    @Override
    public Manager getByIdOrThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new EntityNotFoundException(UserExceptionCodes.USER_WITH_ID_DOES_NOT_EXIST, id));
    }

    @Override
    public Manager create(ManagerDto managerDto) {
        checkCreationPossibility(managerDto);
        var manager = managerMapper.toEntity(managerDto);
        manager.setRole(Role.ROLE_MANAGER);
        authService.createCredentials(manager.getId(), managerDto.getPassword());
        return managerRepository.save(manager);
    }

    private void checkCreationPossibility(ManagerDto managerDto) {
        if (HashUtils.isEmptySha256Hash(managerDto.getPassword())) {
            throw new ValidationException(UserExceptionCodes.USER_PASSWORD_IS_EMPTY);
        }
    }

    @Override
    public Manager update(Long id, ManagerDto managerDto) {
        var manager = getByIdOrThrow(id);
        manager.setName(managerDto.getName());
        manager.setSurname(managerDto.getSurname());
        manager.setEmail(managerDto.getEmail());
        manager.setPhone(managerDto.getPhone());
        return managerRepository.save(manager);
    }
}
