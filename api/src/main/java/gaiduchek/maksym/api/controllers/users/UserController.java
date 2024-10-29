package gaiduchek.maksym.api.controllers.users;

import gaiduchek.maksym.api.dto.users.UserDto;
import gaiduchek.maksym.api.mappers.UserMapper;
import gaiduchek.maksym.api.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static gaiduchek.maksym.api.constants.HeadersConstants.X_API_KEY;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public UserDto get(@PathVariable Long id) {
        var user = userService.getByIdOrThrow(id);
        return userMapper.toDto(user);
    }

    @GetMapping("/internal/{id}")
    @PreAuthorize("@accessService.checkTechnicalEndpoint(#apiKey)")
    public UserDto getByIdInternal(@PathVariable Long id,
                                   @RequestHeader(value = X_API_KEY, required = false) String apiKey) {
        var user = userService.getByIdOrThrow(id);
        return userMapper.toDto(user);
    }

    @GetMapping("/internal/emails")
    @PreAuthorize("@accessService.checkTechnicalEndpoint(#apiKey)")
    public UserDto getByEmailInternal(@RequestParam String email,
                                      @RequestHeader(value = X_API_KEY, required = false) String apiKey) {
        var user = userService.getByEmailOrThrow(email);
        return userMapper.toDto(user);
    }
}
