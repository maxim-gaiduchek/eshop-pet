package gaiduchek.maksym.api.exceptions;

import gaiduchek.maksym.api.exceptions.exceptioncodes.ExceptionCodes;

public class AuthorizationException extends AbstractException {

    public AuthorizationException(ExceptionCodes code) {
        super(code);
    }

    public AuthorizationException(ExceptionCodes code, Object... formatArgs) {
        super(code, formatArgs);
    }
}
