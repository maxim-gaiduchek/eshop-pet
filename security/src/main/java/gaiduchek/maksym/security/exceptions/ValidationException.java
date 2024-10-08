package gaiduchek.maksym.security.exceptions;

import gaiduchek.maksym.security.exceptions.exceptioncodes.ExceptionCodes;

public class ValidationException extends AbstractException {

    public ValidationException(ExceptionCodes code) {
        super(code);
    }

    public ValidationException(ExceptionCodes code, Object... formatArgs) {
        super(code, formatArgs);
    }

    public ValidationException(String code, String description) {
        super(code, description);
    }
}
