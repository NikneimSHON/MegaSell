package sample.dao.Interface;

import java.util.Optional;

public interface BaseDaoImpl<K,E> {
    Optional<K> findById(E id);
    boolean save(K entity);
    boolean update(K entity);
    boolean delete(E id);
}
