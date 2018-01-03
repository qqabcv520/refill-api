package cn.mifan123.refill.service.impl;

import cn.mifan123.refill.common.exception.BusinessException;
import cn.mifan123.refill.service.EntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by 米饭 on 2017-05-26.
 */
public abstract class EntityServiceImpl<P, V, ID extends Serializable> implements EntityService<P, V, ID> {

    /**
     * 获取当前EntityService的JpaRepository
     * @return
     */
    abstract public JpaRepository<P, ID> getJpaRepository();


    @Transactional
    @Override
    public V save(V entity) {
        P p = voToPo(entity);
        return poToVo(getJpaRepository().save(p));
    }

    @Transactional
    @Override
    public Iterable<V> save(Iterable<V> entities) {
        Iterable<P> pIterable = voListToPoList(entities);
        return poListToVoList(getJpaRepository().save(pIterable));
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public V findOne(ID id) {
        P p = getJpaRepository().findOne(id);
        if ( p == null) {
            throw new BusinessException("ID不存在：" + id);
        }
        return poToVo(p);
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public boolean exists(ID id) {
        return getJpaRepository().exists(id);
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public Iterable<V> findAll() {
        return poListToVoList(getJpaRepository().findAll());
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public Iterable<V> findAll(Iterable<ID> ids) {
        return poListToVoList(getJpaRepository().findAll(ids));
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public long count() {
        return getJpaRepository().count();
    }

    @Transactional
    @Override
    public void delete(ID id) {
        getJpaRepository().delete(id);
    }

    @Transactional
    @Override
    public void delete(V entity) {
        getJpaRepository().delete(voToPo(entity));
    }

    @Transactional
    @Override
    public void delete(Iterable<? extends V> entities) {
        getJpaRepository().delete(voListToPoList(entities));
    }

    @Transactional
    @Override
    public void deleteAll() {
        getJpaRepository().deleteAll();
    }


    /**
     * 值对象转数据库对象
     * @param vo
     * @param <VO> 被转换成的值对象class
     * @return
     */
    protected <VO> P voToPo(VO vo)  {
        P po = null;
        try {
            po = getPClass().newInstance();
            BeanUtils.copyProperties(vo, po);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return po;
    }


    /**
     * 值对象转数据库对象
     * @param voList
     * @param <VO> 被转换成的值对象class
     * @return
     */
    protected <VO> ArrayList<P> voListToPoList(Iterable<VO> voList) {
        ArrayList<P> list = new ArrayList<>();
        for(VO vo : voList) {
            list.add(voToPo(vo));
        }
        return list;
    }


    /**
     * 数据库对象转值对象
     * @param po
     * @param voClass
     * @param <VO>
     * @return
     */
    protected <VO> VO poToVo(P po, Class<VO> voClass)  {
        VO vo = null;
        try {
            vo = voClass.newInstance();
            BeanUtils.copyProperties(po, vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return vo;
    }

    /**
     * 数据库对象转值对象
     * @param po
     * @return
     */
    protected V poToVo(P po)  {
        return poToVo(po, getVClass());
    }




    /**
     * 数据库对象转值对象
     * @param poList
     * @param voClass
     * @param <VO>
     * @return
     */
    protected <VO> ArrayList<VO> poListToVoList(Iterable<P> poList, Class<VO> voClass) {
        ArrayList<VO> list = new ArrayList<>();
        for(P po : poList) {
            list.add(poToVo(po, voClass));
        }
        return list;
    }

    /**
     * 数据库对象转值对象
     * @param poList
     * @return
     */
    protected ArrayList<V> poListToVoList(Iterable<P> poList) {
        ArrayList<V> list = new ArrayList<>();
        for(P po : poList) {
            list.add(poToVo(po));
        }
        return list;
    }


    @SuppressWarnings("unchecked")
    protected Class<P> getPClass() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<P>)params[0];
    }

    @SuppressWarnings("unchecked")
    protected Class<V> getVClass() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<V>)params[1];
    }
}
