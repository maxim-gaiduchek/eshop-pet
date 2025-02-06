package gaiduchek.maksym.api.services.impl;

import gaiduchek.maksym.api.model.Administrator;
import gaiduchek.maksym.api.repository.AdministratorRepository;
import gaiduchek.maksym.api.services.interfaces.AdministratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministratorServiceImpl implements AdministratorService {

    private final AdministratorRepository administratorRepository;

    @Override
    public Optional<Administrator> findById(Long id) {
        return administratorRepository.findById(id);
    }
}
