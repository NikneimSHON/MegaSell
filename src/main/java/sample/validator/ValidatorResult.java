package sample.validator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ValidatorResult {
    private final List<Error> errors = new ArrayList<>();

    public void addError(Error error) {
        errors.add(error);
    }

    public Boolean isValid() {
        return errors.isEmpty();
    }
}
