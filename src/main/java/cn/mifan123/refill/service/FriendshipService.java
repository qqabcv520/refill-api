package cn.mifan123.refill.service;

import cn.mifan123.refill.common.vo.Friendship;
import cn.mifan123.refill.entity.FriendshipEntity;

import java.util.List;

public interface FriendshipService extends EntityService<FriendshipEntity, Friendship, Integer> {


    /**
     *
     * @param userId
     * @param page 从0开始
     * @param size
     * @return
     */
    List<Friendship> findAllByUserId(Integer userId, Integer page, Integer size);
}
