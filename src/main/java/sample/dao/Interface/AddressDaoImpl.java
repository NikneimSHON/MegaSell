package sample.dao.Interface;

import sample.entity.AddressEntity;
import sample.entity.EmployEntity;

import java.util.List;

public interface AddressDaoImpl extends BaseDaoImpl<AddressEntity,Integer> {
    List<AddressEntity> findAllByEmployId(Integer employId);
    List<AddressEntity> findAll();

}
