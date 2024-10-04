package gaiduchek.maksym.security.exceptions;

import gaiduchek.maksym.security.exceptions.exceptioncodes.ExceptionCodes;

public class AuthorizationException extends AbstractException {

    public AuthorizationException(ExceptionCodes code) {
        super(code);
    }

    public AuthorizationException(ExceptionCodes code, Object... formatArgs) {
        super(code, formatArgs);
    }

    public AuthorizationException(String code, String description) {
        super(code, description);
    }
}
