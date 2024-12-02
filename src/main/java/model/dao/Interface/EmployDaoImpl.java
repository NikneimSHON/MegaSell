package model.dao.Interface;

import model.entity.EmployEntity;
import model.entity.dto.TypePermissionDTO;
import model.entity.dto.TypeActivityDTO;

import java.util.List;
import java.util.Optional;

public interface EmployDaoImpl extends BaseDaoImpl<EmployEntity, Long> {
    List<EmployEntity> findAll();
    TypeActivityDTO getTypeActivity(Long activityId);
    TypePermissionDTO getTypePermission(Long permissionId);


}
