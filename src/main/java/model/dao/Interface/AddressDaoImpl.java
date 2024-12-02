package model.dao.Interface;

import model.entity.AddressEntity;
import model.entity.EmployEntity;

import java.util.List;

public interface AddressDaoImpl extends BaseDaoImpl<AddressEntity,Long> {
    List<AddressEntity> findAll();
    List<AddressEntity> findEmployByAddress(Long id);

}
