package gaiduchek.maksym.security.configurations;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import static gaiduchek.maksym.security.constants.HeadersConstants.X_API_KEY;

@Component
public class ApiKeyRequestInterceptor implements RequestInterceptor {

    @Value("${security.api-key}")
    private String apiKey;

    @Override
    public void apply(RequestTemplate template) {
        template.header(X_API_KEY, apiKey);
    }
}
