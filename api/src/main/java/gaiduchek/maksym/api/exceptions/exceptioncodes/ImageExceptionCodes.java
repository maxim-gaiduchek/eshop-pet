package gaiduchek.maksym.api.exceptions.exceptioncodes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ImageExceptionCodes implements ExceptionCodes{

    IMAGE_CANNOT_BE_UPLOADED("API-IMG-001", "Image cannot be uploaded"),
    ;

    public final String code;
    public final String description;
}
