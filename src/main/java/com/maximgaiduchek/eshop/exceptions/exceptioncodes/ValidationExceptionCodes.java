package com.maximgaiduchek.eshop.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ValidationExceptionCodes implements ExceptionCodes {

    INVALID_DTO("SHOP-VLD-001", "Invalid DTO");

    public final String code;
    public final String description;
}
