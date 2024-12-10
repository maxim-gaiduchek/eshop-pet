package gaiduchek.maksym.api.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FilterExceptionCodes implements ExceptionCodes{

    FILTER_DOES_NOT_EXIST("API-FLT-001", "Filter with id %d does not exist"),
    FILTER_NAME_ALREADY_EXISTS("API-FLT-002", "Filter name '%s' in filter category id %d already exists"),
    ;

    public final String code;
    public final String description;
}
