package gaiduchek.maksym.api.security.model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Getter
@RequiredArgsConstructor
public enum UnsecuredEndpoint {

    GET_SWAGGER("/api/swagger-ui/.*", GET),
    GET_API_DOCS("/api/v3/api-docs.*", GET),
    INTERNAL_GET_USER("/api/users/internal.*", GET),
    GET_PRODUCTS("/api/products.*", GET),
    ;

    private final String urlPattern;
    private final HttpMethod httpMethod;

    public static boolean isEndpointUnsecured(HttpServletRequest request) {
        var url = request.getRequestURI();
        var method = HttpMethod.valueOf(request.getMethod());
        return Arrays.stream(values())
                .filter(endpoint -> url.matches(endpoint.getUrlPattern()))
                .anyMatch(endpoint -> method.equals(endpoint.getHttpMethod()));
    }
}
