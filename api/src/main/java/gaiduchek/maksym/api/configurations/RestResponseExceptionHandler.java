package gaiduchek.maksym.api.configurations;

import gaiduchek.maksym.api.exceptions.AbstractException;
import gaiduchek.maksym.api.exceptions.AccessException;
import gaiduchek.maksym.api.exceptions.AuthorizationException;
import gaiduchek.maksym.api.exceptions.EntityNotFoundException;
import gaiduchek.maksym.api.exceptions.ValidationException;
import gaiduchek.maksym.api.exceptions.exceptioncodes.AccessExceptionCodes;
import gaiduchek.maksym.api.exceptions.exceptioncodes.ValidationExceptionCodes;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class RestResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String STATUS_KEY = "status";
    private static final String TIMESTAMP_KEY = "timestamp";
    private static final String CODE_KEY = "code";
    private static final String DESCRIPTION_KEY = "description";

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleUnexpectedException(Exception exception) {
        log.error("Unexpected exception occurred: ", exception);
        var body = new LinkedHashMap<>();
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<Object> handleAccessDenies(AccessDeniedException exception) {
        var body = getResponse(HttpStatus.FORBIDDEN, new AccessException(AccessExceptionCodes.ACCESS_DENIED));
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(AbstractException exception) {
        var body = getResponse(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {AuthorizationException.class})
    protected ResponseEntity<Object> handleAuthorizationException(AuthorizationException exception) {
        var body = getResponse(HttpStatus.UNAUTHORIZED, exception);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Object> handleValidationException(ValidationException exception) {
        var body = getResponse(HttpStatus.BAD_REQUEST, exception);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NonNull HttpHeaders headers,
                                                                  @NonNull HttpStatusCode status,
                                                                  @NonNull WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName;
            if (error instanceof FieldError) {
                fieldName = ((FieldError) error).getField();
            } else {
                fieldName = error.getObjectName();
            }
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(getInvalidDtoResponse(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> getResponse(HttpStatus status, AbstractException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(STATUS_KEY, status.toString());
        body.put(TIMESTAMP_KEY, new Date());
        body.put(CODE_KEY, exception.getCode());
        body.put(DESCRIPTION_KEY, exception.getDescription());
        return body;
    }

    private Map<String, Object> getInvalidDtoResponse(Map<String, String> errors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(STATUS_KEY, HttpStatus.BAD_REQUEST.toString());
        body.put(TIMESTAMP_KEY, new Date());
        body.put(CODE_KEY, ValidationExceptionCodes.INVALID_DTO.getCode());
        body.put(DESCRIPTION_KEY, ValidationExceptionCodes.INVALID_DTO.getDescription());
        body.put("errors", errors);
        return body;
    }
}
