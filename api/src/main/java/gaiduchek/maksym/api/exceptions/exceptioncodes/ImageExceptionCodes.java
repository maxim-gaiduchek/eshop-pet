package gaiduchek.maksym.api.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ImageExceptionCodes implements ExceptionCodes{

    IMAGE_DOES_NOT_EXIST("API-IMG-001", "Image with id %d does not exist"),
    IMAGE_CANNOT_BE_UPLOADED("API-IMG-002", "Image cannot be uploaded"),
    IMAGE_CANNOT_BE_DELETED("API-IMG-003", "Image cannot be deleted"),
    ;

    public final String code;
    public final String description;
}
