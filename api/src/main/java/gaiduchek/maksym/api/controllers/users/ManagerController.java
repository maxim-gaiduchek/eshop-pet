package gaiduchek.maksym.api.controllers.users;

import gaiduchek.maksym.api.dto.users.ManagerDto;
import gaiduchek.maksym.api.mappers.ManagerMapper;
import gaiduchek.maksym.api.services.interfaces.ManagerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;
    private final ManagerMapper managerMapper;

    @GetMapping("/{id}")
    @PreAuthorize("@accessService.isWorker()")
    public ManagerDto get(@PathVariable Long id) {
        var manager = managerService.getByIdOrThrow(id);
        return managerMapper.toDto(manager);
    }

    @PostMapping
    @PreAuthorize("@accessService.isAdministrator()")
    public ManagerDto create(@RequestBody @Valid ManagerDto managerDto) {
        var manager = managerService.create(managerDto);
        return managerMapper.toDto(manager);
    }

    @PutMapping("/{id}")
    @PreAuthorize("@accessService.isAdministrator()")
    public ManagerDto update(@PathVariable Long id,
                             @RequestBody @Valid ManagerDto managerDto) {
        var manager = managerService.update(id, managerDto);
        return managerMapper.toDto(manager);
    }
}
