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
        P p = VoToPo(entity);
        return PoToVo(jpaRepository.save(p), getVClass());
    }

    @Transactional
    @Override
    public Iterable<V> save(Iterable<V> entities) {
        Iterable<P> pIterable = VoListToPoList(entities);
        return PoListToVoList(jpaRepository.save(pIterable), getVClass());
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public V findOne(ID id) {
        return PoToVo(jpaRepository.findOne(id), getVClass());
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public boolean exists(ID id) {
        return jpaRepository.exists(id);
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public Iterable<V> findAll() {
        return PoListToVoList(jpaRepository.findAll(), getVClass());
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public Iterable<V> findAll(Iterable<ID> ids) {
        return PoListToVoList(jpaRepository.findAll(ids), getVClass());
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
        jpaRepository.delete(VoToPo(entity));
    }

    @Transactional
    @Override
    public void delete(Iterable<? extends V> entities) {
        jpaRepository.delete(VoListToPoList(entities));
    }

    @Transactional
    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }


    protected <VO> P VoToPo(VO vo)  {
        P po = null;
        try {
            po = getPClass().newInstance();
            BeanUtils.copyProperties(vo, po);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return po;
    }

    protected <VO> Iterable<P> VoListToPoList(Iterable<VO> voList) {
        ArrayList<P> list = new ArrayList<>();
        for(VO vo : voList) {
            list.add(VoToPo(vo));
        }
        return list;
    }

    protected <VO> VO PoToVo(P po, Class<VO> voClass)  {
        VO vo = null;
        try {
            vo = voClass.newInstance();
            BeanUtils.copyProperties(po, vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return vo;
    }

    protected <VO> Iterable<VO> PoListToVoList(Iterable<P> poList, Class<VO> voClass) {

        ArrayList<VO> list = new ArrayList<>();
        for(P po : poList) {
            list.add(PoToVo(po, voClass));
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
