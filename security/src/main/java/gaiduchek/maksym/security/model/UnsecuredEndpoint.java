package gaiduchek.maksym.security.model;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;

import java.util.Arrays;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Getter
@RequiredArgsConstructor
public enum UnsecuredEndpoint {

    GET_ALL("/security/.*", GET),
    POST_ALL("/security/.*", POST),
    POST_CREDENTIALS("/security/internal/credentials", POST),
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
