package com.maximgaiduchek.eshop.exceptions;

import com.maximgaiduchek.eshop.exceptions.exceptioncodes.ExceptionCodes;

public class ValidationException extends AbstractException {

    public ValidationException(ExceptionCodes code) {
        super(code);
    }

    public ValidationException(ExceptionCodes code, Object... formatArgs) {
        super(code, formatArgs);
    }
}
