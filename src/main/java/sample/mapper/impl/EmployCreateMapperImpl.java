package sample.mapper.impl;

import sample.dto.impl.EmployCreateDto;
import sample.entity.EmployEntity;
import sample.mapper.EmployCreateMapper;
import sample.util.DateLocalFormat;


public class EmployCreateMapperImpl implements EmployCreateMapper<EmployCreateDto, EmployEntity> {

    private static EmployCreateMapperImpl instance;

    private EmployCreateMapperImpl() {

    }

    public static EmployCreateMapperImpl getInstance() {
        if (instance == null) {
            instance = new EmployCreateMapperImpl();
        }
        return instance;
    }

    @Override
    public EmployEntity toCreateEmployEntity(EmployCreateDto employDto) {
        return EmployEntity.builder()
                .birthDate(DateLocalFormat.format(employDto.getBirthDate()))
                .number(employDto.getNumber())
                .password(employDto.getPassword())
                .activityId(employDto.getActivityId())
                .accessPermissionId(employDto.getAccessPermissionId())
                .build();
    }
}
