package gaiduchek.maksym.security.configurations;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import gaiduchek.maksym.security.dto.ErrorDto;
import gaiduchek.maksym.security.exceptions.AbstractException;
import gaiduchek.maksym.security.exceptions.AccessException;
import gaiduchek.maksym.security.exceptions.EntityNotFoundException;
import gaiduchek.maksym.security.exceptions.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
public class CustomErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    @Override
    public Exception decode(String methodKey, Response response) {
        try {
            var body = fetchBody(response);
            return switch (response.status()) {
                case 400 -> new ValidationException(body.getCode(), body.getDescription());
                case 401 -> new AccessException(body.getCode(), body.getDescription());
                case 404 -> new EntityNotFoundException(body.getCode(), body.getDescription());
                default -> new AbstractException(body.getCode(), body.getDescription());
            };
        } catch (IOException | NullPointerException e) {
            log.error("Error occurred while parsing response body", e);
            return new AbstractException("SEC-GEN-001", e.getMessage());
        }
    }

    private ErrorDto fetchBody(Response response) throws IOException, NullPointerException {
        var bodyJson = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
        return objectMapper.readValue(bodyJson, ErrorDto.class);
    }
}

