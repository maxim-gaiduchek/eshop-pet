package gaiduchek.maksym.security.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.codec.ErrorDecoder;
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
