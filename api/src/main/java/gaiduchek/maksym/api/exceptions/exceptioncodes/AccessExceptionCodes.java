package gaiduchek.maksym.api.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AccessExceptionCodes implements ExceptionCodes {

    TOKEN_INVALID("API-AUTH-001", "Invalid JWT"),
    TOKEN_EXPIRED("API-AUTH-002", "JWT is expired"),
    TOKEN_REQUIRED("API-AUTH-003", "A jwt token is required to access this resource"),
    ACCESS_DENIED("API-AUTH-004", "User does not have access to this resource");

    public final String code;
    public final String description;
}
