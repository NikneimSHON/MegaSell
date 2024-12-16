package sample.validator;

import lombok.experimental.UtilityClass;

import java.util.regex.Pattern;

@UtilityClass
public class PasswordValidator {
    private static final String PASSWORD_PATTERN = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";

    public static boolean isValid(String password) {
        return Pattern.matches(PASSWORD_PATTERN, password);
    }

    public static boolean isValidPassword(String password, String repeat_password) {
        return password.equals(repeat_password);
    }
}
