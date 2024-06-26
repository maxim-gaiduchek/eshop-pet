package com.maximgaiduchek.eshop.exceptions;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.maximgaiduchek.eshop.exceptions.exceptioncodes.ExceptionCodes;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@JsonIncludeProperties({"code", "description"})
@Data
public class AbstractException extends RuntimeException {

    private final String code;
    private final String description;

    public AbstractException(ExceptionCodes exceptionCode) {
        code = exceptionCode.getCode();
        description = exceptionCode.getDescription();
    }

    public AbstractException(ExceptionCodes exceptionCode, Object... formatArgs) {
        this.code = exceptionCode.getCode();
        this.description = exceptionCode.getDescription().formatted(formatArgs);
    }
}
