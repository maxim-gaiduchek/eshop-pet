package gaiduchek.maksym.api.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
import gaiduchek.maksym.security.configurations.ApiKeyRequestInterceptor;
import gaiduchek.maksym.security.configurations.CustomErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InternalFeignConfiguration {

    @Bean
    public ApiKeyRequestInterceptor apiKeyRequestInterceptor() {
        return new ApiKeyRequestInterceptor();
    }

    @Bean
    public ErrorDecoder errorDecoder(ObjectMapper objectMapper) {
        return new CustomErrorDecoder(objectMapper);
    }
}
