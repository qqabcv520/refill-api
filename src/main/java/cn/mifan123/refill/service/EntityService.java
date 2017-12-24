package cn.mifan123.refill.service;

import java.io.Serializable;

/**
 * Created by 米饭 on 2017-05-26.
 */
public interface EntityService<P, V, ID extends Serializable> {
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
