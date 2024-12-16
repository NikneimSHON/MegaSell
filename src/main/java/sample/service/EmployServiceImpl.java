package sample.service;

import sample.dao.EmployDao;
import sample.dto.impl.EmployBaseInfoDto;
import sample.dto.impl.EmployCreateDto;
import sample.exception.ValidationException;
import sample.mapper.EmployCreateMapper;
import sample.mapper.impl.EmployBaseMapperImpl;
import sample.mapper.impl.EmployCreateMapperImpl;
import sample.validator.CreateEmployValidator;


import java.util.Optional;

public class EmployServiceImpl {
    private static EmployServiceImpl instance;
    private final static EmployDao employDao = EmployDao.getInstance();
    private final static EmployBaseMapperImpl employMapper = EmployBaseMapperImpl.getInstance();
    private final static EmployCreateMapperImpl employCreateMapper = EmployCreateMapperImpl.getInstance();
    private final static CreateEmployValidator createEmployValidator = CreateEmployValidator.getInstance();

    private EmployServiceImpl() {

    }

    public static synchronized EmployServiceImpl getInstance() {
        if (instance == null) {
            instance = new EmployServiceImpl();
        }
        return instance;
    }

    public Integer create(EmployCreateDto employDto) {
        var validationResult = createEmployValidator.isValid(employDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var employEntity = employCreateMapper.toCreateEmployEntity(employDto);
        employDao.save(employEntity);
        return employEntity.getId();
    }

    public Optional<EmployBaseInfoDto> login(String number, String password) {
        return employDao.getEmployByNumberAndPassword(number, password).map(employMapper::toEmployBaseInfoDto);
    }

    public Optional<EmployBaseInfoDto> findEmployById(Integer id) {
        return employDao.findById(id).map(employMapper::toEmployBaseInfoDto);
    }

    public Optional<EmployBaseInfoDto> findEmployByNumber(String number) {
        return employDao.checkEmptyNumber(number).map(employMapper::toEmployBaseInfoDto);
    }
}
