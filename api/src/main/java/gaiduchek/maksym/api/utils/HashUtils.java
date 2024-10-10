package gaiduchek.maksym.api.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class HashUtils {

    private static final String SHA256_HASHED_EMPTY_STRING = "e3b0c44298fc1c149afbf4c8996fb92427ae41e4649b934ca495991b7852b855";

    public static boolean isEmptySha256Hash(String str) {
        return SHA256_HASHED_EMPTY_STRING.equals(str);
    }
}
