package cn.mifan123.refill.service.impl;

import cn.mifan123.refill.service.EntityService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 米饭 on 2017-05-26.
 */
public abstract class EntityServiceImpl<T, ID extends Serializable> implements EntityService<T, ID> {

    /**
     * 由业务类实现
     * @return
     */
    public abstract JpaRepository<T, ID> getEntityDao();

    @Transactional
    @Override
    public <S extends T> S save(S entity) {
        return getEntityDao().save(entity);
    }

    @Transactional
    @Override
    public <S extends T> Iterable<S> save(Iterable<S> entities) {
        return getEntityDao().save(entities);
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public T findOne(ID id) {
        return getEntityDao().findOne(id);
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public boolean exists(ID id) {
        return getEntityDao().exists(id);
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public Iterable<T> findAll() {
        return getEntityDao().findAll();
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        return getEntityDao().findAll(ids);
    }

    @Transactional(readOnly=true, isolation= Isolation.READ_COMMITTED)
    @Override
    public long count() {
        return getEntityDao().count();
    }

    @Transactional
    @Override
    public void delete(ID id) {
        getEntityDao().delete(id);
    }

    @Transactional
    @Override
    public void delete(T entity) {
        getEntityDao().delete(entity);
    }

    @Transactional
    @Override
    public void delete(Iterable<? extends T> entities) {
        getEntityDao().delete(entities);
    }

    @Transactional
    @Override
    public void deleteAll() {
        getEntityDao().deleteAll();
    }

    public <VO> VO PoToVo(T po,  Class<VO> voClass)  {
        VO vo = null;
        try {
            vo = voClass.newInstance();
            BeanUtils.copyProperties(po, vo);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }

        return vo;
    }

    public <VO> List<VO> PoListToVoList(Iterable<T> poList, Class<VO> voClass) {

        List<VO> list = new ArrayList<>();

        for(T po : poList) {
            list.add(PoToVo(po, voClass));
        }
        return list;
    }

}
