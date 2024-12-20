package gaiduchek.maksym.api.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserExceptionCodes implements ExceptionCodes{

    USER_WITH_ID_DOES_NOT_EXIST("API-USR-001", "User with id %d does not exist"),
    USER_DOES_NOT_EXIST("API-USR-002", "User does not exist"),
    USER_PASSWORD_IS_EMPTY("API-USR-003", "User password is empty"),
    USER_WITH_EMAIL_ALREADY_EXISTS("API-USR-004", "User with email \"%s\" already exists"),
    ;

    public final String code;
    public final String description;
}
