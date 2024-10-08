package gaiduchek.maksym.security.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserAuthExceptionCodes implements ExceptionCodes {

    USER_AUTH_DOES_NOT_EXIST("SEC-USAT-001", "User auth does not exist"),
    ;

    public final String code;
    public final String description;
}
