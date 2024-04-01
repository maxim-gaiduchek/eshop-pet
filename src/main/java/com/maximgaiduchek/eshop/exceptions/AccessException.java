package com.maximgaiduchek.eshop.exceptions;


import com.maximgaiduchek.eshop.exceptions.exceptioncodes.ExceptionCodes;

public class AccessException extends AbstractException {

    public AccessException(ExceptionCodes code) {
        super(code);
    }
}
