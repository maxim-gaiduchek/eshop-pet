package com.maximgaiduchek.eshop.exceptions;

import com.maximgaiduchek.eshop.exceptions.exceptioncodes.ExceptionCodes;

public class AuthorizationException extends AbstractException {

    public AuthorizationException(ExceptionCodes code) {
        super(code);
    }

    public AuthorizationException(ExceptionCodes code, Object... formatArgs) {
        super(code, formatArgs);
    }
}
