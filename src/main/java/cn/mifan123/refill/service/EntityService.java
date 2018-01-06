package cn.mifan123.refill.service;

import java.io.Serializable;


/**
 *
 * @param <V> 值对象
 * @param <ID> 主键
 */
public interface EntityService<V, ID extends Serializable> {
    V save(V entity);

    Iterable<V> save(Iterable<V> entities);

    V findOne(ID id);

    boolean exists(ID id);

    Iterable<V> findAll();

    Iterable<V> findAll(Iterable<ID> ids);

    long count();

    void delete(ID id);

    void delete(V entity);

    void delete(Iterable<? extends V> entities);

    void deleteAll();



}
