package sample.service;

import sample.dao.EmployDaoImpl;
import sample.dto.impl.EmployBaseInfoDto;
import sample.dto.impl.EmployCreateDto;
import sample.dto.impl.EmployPersonalDataDto;
import sample.exception.ValidationException;
import sample.mapper.impl.EmployBaseMapperImpl;
import sample.mapper.impl.EmployCreateMapperImpl;
import sample.mapper.impl.EmployUpdateMapperImpl;
import sample.util.LoginResult;
import sample.validator.CreateEmployValidator;
import sample.validator.UpdateEmployValidator;


import java.util.Optional;

public class EmployServiceImpl {
    private static EmployServiceImpl instance;
    private final static EmployDaoImpl EMPLOY_DAO_IMPL = EmployDaoImpl.getInstance();
    private final static EmployBaseMapperImpl employMapper = EmployBaseMapperImpl.getInstance();
    private final static EmployUpdateMapperImpl employUpdateMapper = EmployUpdateMapperImpl.getInstance();
    private final static EmployCreateMapperImpl employCreateMapper = EmployCreateMapperImpl.getInstance();
    private final static CreateEmployValidator createEmployValidator = CreateEmployValidator.getInstance();
    private final static UpdateEmployValidator updateEmployValidator = UpdateEmployValidator.getInstance();

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
        EMPLOY_DAO_IMPL.save(employEntity);
        return employEntity.getId();
    }

    public Integer update(EmployPersonalDataDto employDto) {
        var validationResult = updateEmployValidator.isValid(employDto);
        if (!validationResult.isValid()) {
            throw new ValidationException(validationResult.getErrors());
        }
        var employEntity = employUpdateMapper.toUpdateEmployEntity(employDto);
        EMPLOY_DAO_IMPL.updatePersonalInfo(employEntity);
        return employEntity.getId();
    }

    public LoginResult login(String number, String password) {
        Optional<EmployBaseInfoDto> employ = EMPLOY_DAO_IMPL.getEmployByNumberAndPassword(number, password)
                .map(employMapper::toEmployBaseInfoDto);

        if (employ.isPresent()) {
            String activity = EMPLOY_DAO_IMPL.getTypeActivity(employ.get().getId());
            if (!"Allowed".equals(activity)) {
                return new LoginResult(Optional.empty(), "Пользователь заблокирован");
            }
        }
        return new LoginResult(employ, "Неверный логин или пароль");
    }

    public Optional<EmployBaseInfoDto> findEmployById(Integer id) {
        return EMPLOY_DAO_IMPL.findById(id).map(employMapper::toEmployBaseInfoDto);
    }

    public Optional<EmployBaseInfoDto> findEmployByNumber(String number) {
        return EMPLOY_DAO_IMPL.checkEmptyNumber(number).map(employMapper::toEmployBaseInfoDto);
    }

    public Optional<EmployBaseInfoDto> findEmployByEmail(String email) {
        return EMPLOY_DAO_IMPL.checkEmptyEmail(email).map(employMapper::toEmployBaseInfoDto);
    }

    public String getTypePermission(Integer id) {
        return EMPLOY_DAO_IMPL.getTypePermission(id);
    }
}
