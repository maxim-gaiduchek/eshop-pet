package gaiduchek.maksym.security.exceptions;

import gaiduchek.maksym.security.exceptions.exceptioncodes.ExceptionCodes;

public class AccessException extends AbstractException {

    public AccessException(ExceptionCodes code) {
        super(code);
    }

    public AccessException(ExceptionCodes code, Object... formatArgs) {
        super(code);
    }

    public AccessException(String code, String description) {
        super(code, description);
    }
}
