package gaiduchek.maksym.api.exceptions;

import gaiduchek.maksym.api.exceptions.exceptioncodes.ExceptionCodes;

public class ValidationException extends AbstractException {

    public ValidationException(ExceptionCodes code) {
        super(code);
    }

    public ValidationException(ExceptionCodes code, Object... formatArgs) {
        super(code, formatArgs);
    }
}
