package cn.mifan123.refill.service;

import cn.mifan123.refill.common.vo.DriftingBottle;

import java.util.List;

public interface DriftingBottleService extends EntityService<DriftingBottle, Integer> {

    /**
     *
     * @param senderId
     * @param page 从0开始
     * @param size
     * @return
     */
    List<DriftingBottle> findAllBySenderId(Integer senderId, Integer page, Integer size);

    /**
     *
     * @param receiverId
     * @param page 从0开始
     * @param size
     * @return
     */
    List<DriftingBottle> findAllByReceiverId(Integer receiverId, Integer page, Integer size);

    /**
     *
     * @param state
     * @param senderId
     * @return
     */
    DriftingBottle findByRand(Integer state, Integer senderId);
}
