package sample.dao.Interface;

import sample.entity.EmployEntity;

import java.util.List;
import java.util.Optional;

public interface EmployDaoImpl extends BaseDaoImpl<EmployEntity, Integer> {
    List<EmployEntity> findAll();
    String getTypeActivity(Integer activityId);
    String getTypePermission(Integer permissionId);
    Optional<EmployEntity> getEmployByNumberAndPassword(String number,String password);
    Optional<EmployEntity> checkEmptyNumber(String number);


}
