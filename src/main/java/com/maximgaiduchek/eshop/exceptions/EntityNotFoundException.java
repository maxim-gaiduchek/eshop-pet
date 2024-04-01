package com.maximgaiduchek.eshop.exceptions;

import com.maximgaiduchek.eshop.exceptions.exceptioncodes.ExceptionCodes;

public class EntityNotFoundException extends AbstractException {

    public EntityNotFoundException(ExceptionCodes code) {
        super(code);
    }

    public EntityNotFoundException(ExceptionCodes code, Object... formatArgs) {
        super(code, formatArgs);
    }
}
