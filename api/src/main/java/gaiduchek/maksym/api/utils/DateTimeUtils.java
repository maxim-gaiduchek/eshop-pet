package gaiduchek.maksym.api.utils;


import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtils {

    private final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public ZonedDateTime convertDateStartDay(String date) {
        var localDate = LocalDate.parse(date, DATE_FORMATTER);
        return convertDateStartDay(localDate);
    }

    public ZonedDateTime convertDateStartDay(LocalDate localDate) {
        return localDate.atTime(0, 0).atZone(ZoneId.of("UTC"));
    }

    public ZonedDateTime convertDateEndDay(String date) {
        var localDate = LocalDate.parse(date, DATE_FORMATTER);
        return convertDateEndDay(localDate);
    }

    public ZonedDateTime convertDateEndDay(LocalDate localDate) {
        return localDate.atTime(23, 59).atZone(ZoneId.of("UTC"));
    }
}
