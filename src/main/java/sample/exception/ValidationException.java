package sample.exception;

import lombok.Getter;

import java.util.List;

import sample.validator.Error;
import sample.validator.Validator;
@Getter
public class ValidationException extends RuntimeException {
    private List<Error> errors;

    public ValidationException(List<Error> errors) {
        this.errors = errors;
    }


}
