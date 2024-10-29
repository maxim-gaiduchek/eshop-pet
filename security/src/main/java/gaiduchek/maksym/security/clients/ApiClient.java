package gaiduchek.maksym.security.clients;

import gaiduchek.maksym.security.configurations.InternalFeignConfiguration;
import gaiduchek.maksym.security.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${feign.api.url}", name = "api-service", configuration = InternalFeignConfiguration.class)
public interface ApiClient {

    @GetMapping("/users/internal/{id}")
    UserDto getByIdOrThrow(@PathVariable Long id);

    @GetMapping("/users/internal/emails")
    UserDto getByEmailOrThrow(@RequestParam String email);
}
