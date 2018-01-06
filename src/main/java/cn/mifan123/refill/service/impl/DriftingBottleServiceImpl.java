package cn.mifan123.refill.service.impl;

import cn.mifan123.refill.common.vo.DriftingBottle;
import cn.mifan123.refill.entity.DriftingBottleEntity;
import cn.mifan123.refill.repository.DriftingBottleRepository;
import cn.mifan123.refill.service.DriftingBottleService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DriftingBottleServiceImpl extends EntityServiceImpl<DriftingBottleEntity, DriftingBottle, Integer> implements DriftingBottleService {

    @Resource
    private DriftingBottleRepository driftingBottleRepository;

    @Override
    public JpaRepository<DriftingBottleEntity, Integer> getJpaRepository() {
        return driftingBottleRepository;
    }
}
