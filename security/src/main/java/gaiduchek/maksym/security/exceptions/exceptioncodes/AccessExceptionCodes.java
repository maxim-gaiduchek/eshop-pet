package gaiduchek.maksym.security.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AccessExceptionCodes implements ExceptionCodes {

    INVALID_PASSWORD("SEC-AUTH-001", "Invalid password"),
    INVALID_JWT("SEC-AUTH-002", "Invalid JWT"),
    EXPIRED_JWT("SEC-AUTH-003", "JWT is expired"),
    ;

    public final String code;
    public final String description;
}
