package gaiduchek.maksym.security.configurations;

import gaiduchek.maksym.security.exceptions.AbstractException;
import gaiduchek.maksym.security.exceptions.AccessException;
import gaiduchek.maksym.security.exceptions.EntityNotFoundException;
import gaiduchek.maksym.security.exceptions.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @ExceptionHandler(value = {AccessException.class})
    protected ResponseEntity<Object> handleAccess(AbstractException exception) {
        Map<String, Object> body = getResponse(HttpStatus.FORBIDDEN, exception);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleEntityNotFound(AbstractException exception) {
        Map<String, Object> body = getResponse(HttpStatus.NOT_FOUND, exception);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Object> handleValidation(ValidationException exception) {
        Map<String, Object> body = getResponse(HttpStatus.BAD_REQUEST, exception);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(getResponse(HttpStatus.BAD_REQUEST, errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, Object> getResponse(HttpStatus status, AbstractException exception) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(STATUS_KEY, status.toString());
        body.put(TIMESTAMP_KEY, new Date());
        body.put(CODE_KEY, exception.getCode());
        body.put(DESCRIPTION_KEY, exception.getDescription());
        return body;
    }

    private Map<String, Object> getResponse(HttpStatus status, Map<String, String> errors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put(STATUS_KEY, status.toString());
        body.put(TIMESTAMP_KEY, new Date());
        body.put("errors", errors);
        return body;
    }
}
