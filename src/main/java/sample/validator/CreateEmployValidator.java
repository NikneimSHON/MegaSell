package sample.validator;

import sample.dto.impl.EmployCreateDto;
import sample.util.DateLocalFormat;

public class CreateEmployValidator implements Validator<EmployCreateDto> {
    private static CreateEmployValidator instance;
    private CreateEmployValidator() {

    }
    public static CreateEmployValidator getInstance() {
        if (instance == null) {
            return new CreateEmployValidator();
        }
        return instance;
    }

    @Override
    public ValidatorResult isValid(EmployCreateDto object) {
        var validatorResult = new ValidatorResult();
        if (!DateLocalFormat.isValid(object.getBirthDate())) {
            validatorResult.addError(Error.of("invalid birthday", "birth day invalid"));
        } else if (!PhoneNumberValidator.isValid(object.getNumber())) {
            validatorResult.addError(Error.of("invalid phone number", "phone number invalid"));
        }else if(!PasswordValidator.isValidPassword(object.getPassword(),object.getRepeat_password())) {
            validatorResult.addError(Error.of("invalid password", "password invalid"));
        } else if (!PasswordValidator.isValid(object.getPassword())) {
            validatorResult.addError(Error.of("invalid password", "password invalid"));
        }
        return validatorResult;
    }
}
