package gaiduchek.maksym.api.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductExceptionCodes implements ExceptionCodes{

    PRODUCT_DOES_NOT_EXIST("API-PRD-001", "Product with id %d does not exist"),
    ;

    public final String code;
    public final String description;
}
