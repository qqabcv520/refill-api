package cn.mifan123.refill.service.impl;

import cn.mifan123.refill.service.EntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by 米饭 on 2017-05-26.
 */
public abstract class EntityServiceImpl<P, V, ID extends Serializable> implements EntityService<P, V, ID> {

    @Resource
    private JpaRepository<P, ID> jpaRepository;


    @Transactional
    @Override
    public V save(V entity) {
        P p = voToPo(entity);
        return poToVo(jpaRepository.save(p), getVClass());
    }

    @Transactional
    @Override
    public Iterable<V> save(Iterable<V> entities) {
        Iterable<P> pIterable = voListToPoList(entities);
        return poListToVoList(jpaRepository.save(pIterable), getVClass());
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public V findOne(ID id) {
        return poToVo(jpaRepository.findOne(id), getVClass());
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public boolean exists(ID id) {
        return jpaRepository.exists(id);
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public Iterable<V> findAll() {
        return poListToVoList(jpaRepository.findAll(), getVClass());
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public Iterable<V> findAll(Iterable<ID> ids) {
        return poListToVoList(jpaRepository.findAll(ids), getVClass());
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public long count() {
        return jpaRepository.count();
    }

    @Transactional
    @Override
    public void delete(ID id) {
        jpaRepository.delete(id);
    }

    @Transactional
    @Override
    public void delete(V entity) {
        jpaRepository.delete(voToPo(entity));
    }

    @Transactional
    @Override
    public void delete(Iterable<? extends V> entities) {
        jpaRepository.delete(voListToPoList(entities));
    }

    @Transactional
    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
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
    protected <VO> Iterable<P> voListToPoList(Iterable<VO> voList) {
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
    protected <VO> Iterable<VO> poListToVoList(Iterable<P> poList, Class<VO> voClass) {

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
    protected Iterable<V> poListToVoList(Iterable<P> poList) {
        return poListToVoList(poList, getVClass());
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
