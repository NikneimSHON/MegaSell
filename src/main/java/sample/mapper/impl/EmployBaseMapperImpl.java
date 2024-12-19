package sample.mapper.impl;

import sample.dto.impl.EmployBaseInfoDto;
import sample.entity.EmployEntity;
import sample.mapper.EmployBaseMapper;


public class EmployBaseMapperImpl implements EmployBaseMapper<EmployBaseInfoDto, EmployEntity> {
    private static EmployBaseMapperImpl instance;

    private EmployBaseMapperImpl() {

    }

    public static EmployBaseMapperImpl getInstance() {
        if (instance == null) {
            instance = new EmployBaseMapperImpl();
        }
        return instance;
    }

    @Override
    public EmployBaseInfoDto toEmployBaseInfoDto(EmployEntity entity) {
        return EmployBaseInfoDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .birthDate(entity.getBirthDate())
                .email(entity.getEmail())
                .number(entity.getNumber())
                .activityId(entity.getActivityId())
                .accessPermissionId(entity.getAccessPermissionId())
                .build();
    }

}
