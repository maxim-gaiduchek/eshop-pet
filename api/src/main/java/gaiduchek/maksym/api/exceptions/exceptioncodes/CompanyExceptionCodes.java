package gaiduchek.maksym.api.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CompanyExceptionCodes implements ExceptionCodes{

    COMPANY_DOES_NOT_EXIST("API-CMP-001", "Company with id %d does not exist"),
    COMPANY_IS_NOT_OWNED_BY_SELLER("API-CMP-002", "Company with id %d is not owned to current seller"),
    ;

    public final String code;
    public final String description;
}
