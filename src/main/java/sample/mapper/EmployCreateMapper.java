package sample.mapper;

public interface EmployCreateMapper<E,K>{
    K toCreateEmployEntity(E employDto);
}
