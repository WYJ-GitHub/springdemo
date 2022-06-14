package springdemo.dao;

import java.util.List;


public interface BaseDao<E> {

    void add(E map);

    void delete(E map);

    void update(E map);

    List<E> select(E map);

    List<E> likeSelect(E entity);

    Long likeSelectCount(E entity);

}
