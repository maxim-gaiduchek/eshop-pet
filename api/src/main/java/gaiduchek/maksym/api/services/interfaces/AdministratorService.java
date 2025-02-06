package gaiduchek.maksym.api.services.interfaces;

import gaiduchek.maksym.api.model.Administrator;

import java.util.Optional;

public interface AdministratorService {

    Optional<Administrator> findById(Long id);
}
