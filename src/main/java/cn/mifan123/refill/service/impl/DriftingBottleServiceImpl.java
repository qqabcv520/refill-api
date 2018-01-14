package cn.mifan123.refill.service.impl;

import cn.mifan123.refill.common.vo.DriftingBottle;
import cn.mifan123.refill.entity.DriftingBottleEntity;
import cn.mifan123.refill.entity.UsersEntity;
import cn.mifan123.refill.repository.DriftingBottleRepository;
import cn.mifan123.refill.repository.UsersRepository;
import cn.mifan123.refill.service.DriftingBottleService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DriftingBottleServiceImpl extends EntityServiceImpl<DriftingBottleEntity, DriftingBottle, Integer> implements DriftingBottleService {

    @Resource
    private DriftingBottleRepository driftingBottleRepository;
    @Resource
    private UsersRepository usersRepository;

    @Override
    public JpaRepository<DriftingBottleEntity, Integer> getJpaRepository() {
        return driftingBottleRepository;
    }

    @Override
    public List<DriftingBottle> findAllBySenderId(Integer senderId, Integer page, Integer size) {
        Pageable pageable = new PageRequest(page==null ? 0 : page, size==null ? Integer.MAX_VALUE : size);
        List<DriftingBottleEntity> driftingBottleEntityList = driftingBottleRepository.findAllBySenderId(senderId, pageable);
        return poListToVoList(driftingBottleEntityList);
    }

    @Override
    public List<DriftingBottle> findAllByReceiverId(Integer receiverId, Integer page, Integer size) {
        Pageable pageable = new PageRequest(page==null ? 0 : page, size==null ? Integer.MAX_VALUE : size);
        List<DriftingBottleEntity> driftingBottleEntityList = driftingBottleRepository.findAllByReceiverId(receiverId, pageable);
        return poListToVoList(driftingBottleEntityList);
    }

    @Override
    public DriftingBottle findByRand(Integer state, Integer senderId) {
        return poToVo(driftingBottleRepository.findByRand(state, senderId));
    }

    @Override
    protected DriftingBottle poToVo(DriftingBottleEntity po) {
        DriftingBottle driftingBottle = super.poToVo(po);
        if(driftingBottle.getSenderId() != null) {
            UsersEntity sender = usersRepository.getOne(driftingBottle.getSenderId());
            if(sender != null) {
                driftingBottle.setSenderName(sender.getNickname());
            }
        }
        if(driftingBottle.getReceiverId() != null) {
            UsersEntity receiver = usersRepository.getOne(driftingBottle.getReceiverId());
            if(receiver != null) {
                driftingBottle.setReceiverName(receiver.getNickname());
            }
        }
        return driftingBottle;
    }
}
