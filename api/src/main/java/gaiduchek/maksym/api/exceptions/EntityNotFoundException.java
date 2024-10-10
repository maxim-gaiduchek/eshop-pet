package gaiduchek.maksym.api.exceptions;

import gaiduchek.maksym.api.exceptions.exceptioncodes.ExceptionCodes;

public class EntityNotFoundException extends AbstractException {

    public EntityNotFoundException(ExceptionCodes code) {
        super(code);
    }

    public EntityNotFoundException(ExceptionCodes code, Object... formatArgs) {
        super(code, formatArgs);
    }

    public EntityNotFoundException(String code, String description) {
        super(code, description);
    }
}
