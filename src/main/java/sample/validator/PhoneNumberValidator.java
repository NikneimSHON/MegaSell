package sample.validator;

import lombok.experimental.UtilityClass;
import sample.service.EmployServiceImpl;

@UtilityClass
public class PhoneNumberValidator {

    private static final String RUSSIAN_PHONE_PATTERN = "^8\\d{10}$";
    private static final EmployServiceImpl serviceEmploy = EmployServiceImpl.getInstance();

    public static boolean isValid(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return false;
        }
        else if(serviceEmploy.findEmployByNumber(phoneNumber).isPresent()){
            return false;
        }
        return phoneNumber.matches(RUSSIAN_PHONE_PATTERN);
    }

}