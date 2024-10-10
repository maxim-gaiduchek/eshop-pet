package gaiduchek.maksym.api.exceptions;

import gaiduchek.maksym.api.exceptions.exceptioncodes.ExceptionCodes;

public class AccessException extends AbstractException {

    public AccessException(ExceptionCodes code) {
        super(code);
    }

    public AccessException(String code, String description) {
        super(code, description);
    }
}
