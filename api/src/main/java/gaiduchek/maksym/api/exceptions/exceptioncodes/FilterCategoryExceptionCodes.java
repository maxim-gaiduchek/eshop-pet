package gaiduchek.maksym.api.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FilterCategoryExceptionCodes implements ExceptionCodes{

    FILTER_CATEGORY_DOES_NOT_EXIST("API-FLTC-001", "Filter category with id %d does not exist"),
    FILTER_CATEGORY_NAME_ALREADY_EXISTS("API-FLTC-002", "Filter category name '%s' already exists"),
    ;

    public final String code;
    public final String description;
}
