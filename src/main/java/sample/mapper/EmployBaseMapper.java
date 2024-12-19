package sample.mapper;

public interface EmployBaseMapper<E,K> {
    E toEmployBaseInfoDto(K entity);
}
