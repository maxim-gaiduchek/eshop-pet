package gaiduchek.maksym.api.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import gaiduchek.maksym.api.exceptions.AuthorizationException;
import gaiduchek.maksym.api.security.model.JwtAuthentication;
import gaiduchek.maksym.api.security.services.interfaces.JwtProvider;
import gaiduchek.maksym.api.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";

    private final JwtProvider jwtProvider;
    private final ObjectMapper objectMapper;
    private final RestResponseExceptionHandler restResponseExceptionHandler;

    public JwtFilter(JwtProvider jwtProvider) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        this.jwtProvider = jwtProvider;
        this.objectMapper = new ObjectMapper();
        this.restResponseExceptionHandler = new RestResponseExceptionHandler();
        this.objectMapper.setDateFormat(dateFormat);
    }

    @Override
    public void doFilterInternal(@NonNull HttpServletRequest request,
                                 @NonNull HttpServletResponse response,
                                 @NonNull FilterChain filterChain) throws IOException, ServletException {
        final String token = getTokenFromRequest(request);
        try {
            jwtProvider.validateAccessToken(token);
            final Claims claims = jwtProvider.getAccessClaims(token);
            final JwtAuthentication jwtInfoToken = JwtUtils.generate(claims);
            jwtInfoToken.setAuthenticated(true);
            SecurityContextHolder.getContext().setAuthentication(jwtInfoToken);
        } catch (AuthorizationException exception) {
            var responseEntity = restResponseExceptionHandler.handleAuthorizationException(exception);
            response.setContentType("application/json");
            response.setStatus(responseEntity.getStatusCode().value());
            response.getWriter().write(objectMapper.writeValueAsString(responseEntity.getBody()));
            return;
        }

        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        final String bearer = request.getHeader(AUTHORIZATION);
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

}
