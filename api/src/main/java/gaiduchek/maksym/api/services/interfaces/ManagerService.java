package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.dto.users.ManagerDto;
import gaiduchek.maksym.api.model.Manager;

import java.util.Optional;

public interface ManagerService {

    Optional<Manager> findById(Long id);

    Manager getByIdOrThrow(Long id);

    Manager create(ManagerDto managerDto);

    Manager update(Long id, ManagerDto managerDto);
}
