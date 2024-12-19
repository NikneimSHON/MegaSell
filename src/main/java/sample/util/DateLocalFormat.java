package sample.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Optional;


@UtilityClass
public class DateLocalFormat {

    private static final int MINIMUM_AGE = 18;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static LocalDate format(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public static boolean isValid(String dateString) {
        return Optional.ofNullable(dateString)
                .map(DateLocalFormat::format)
                .filter(DateLocalFormat::isOldEnough)
                .filter(DateLocalFormat::isNotInFuture)
                .isPresent();
    }

    private static boolean isOldEnough(LocalDate date) {
        return ChronoUnit.YEARS.between(date, LocalDate.now()) >= MINIMUM_AGE;
    }

    private static boolean isNotInFuture(LocalDate date) {
        return !date.isAfter(LocalDate.now());
    }
}
