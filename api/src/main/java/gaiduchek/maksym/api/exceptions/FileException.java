package gaiduchek.maksym.api.exceptions;

import gaiduchek.maksym.api.exceptions.exceptioncodes.ExceptionCodes;

public class FileException extends AbstractException {

    public FileException(ExceptionCodes code) {
        super(code);
    }

    public FileException(ExceptionCodes code, Object... formatArgs) {
        super(code, formatArgs);
    }

    public FileException(String code, String description) {
        super(code, description);
    }
}
