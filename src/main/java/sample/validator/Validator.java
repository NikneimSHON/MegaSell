package sample.validator;

public interface Validator<T>{
    ValidatorResult isValid(T object);

}
