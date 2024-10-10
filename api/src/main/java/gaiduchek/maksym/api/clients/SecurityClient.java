package gaiduchek.maksym.api.clients;

import gaiduchek.maksym.api.configurations.InternalFeignConfiguration;
import gaiduchek.maksym.api.dto.users.UserCredentialsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${feign.api.url}", name = "api-service", configuration = InternalFeignConfiguration.class)
public interface SecurityClient {

    @PostMapping("/credentials")
    void createCredentials(@RequestBody UserCredentialsDto userCredentialsDto);
}
