package cn.mifan123.refill.service.impl;

import cn.mifan123.refill.common.vo.Friendship;
import cn.mifan123.refill.entity.FriendshipEntity;
import cn.mifan123.refill.entity.UsersEntity;
import cn.mifan123.refill.repository.FriendshipRepository;
import cn.mifan123.refill.repository.UsersRepository;
import cn.mifan123.refill.service.FriendshipService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FriendshipServiceImpl extends EntityServiceImpl<FriendshipEntity, Friendship, Integer> implements FriendshipService {

    @Resource
    private FriendshipRepository friendshipRepository;
    @Resource
    private UsersRepository usersRepository;

    @Override
    public List<Friendship> findAllByUserId(Integer userId, Integer page, Integer size) {
        Pageable pageable = new PageRequest(page==null ? 0 : page, size==null ? Integer.MAX_VALUE : size);
        List<FriendshipEntity> friendshipEntityList = friendshipRepository.findAllByUserId(userId, pageable);
        return poListToVoList(friendshipEntityList);
    }

    @Override
    public JpaRepository<FriendshipEntity, Integer> getJpaRepository() {
        return friendshipRepository;
    }

    @Override
    protected Friendship poToVo(FriendshipEntity po) {
        if(po == null) {
            return null;
        }
        Friendship friendship = super.poToVo(po);
        UsersEntity usersEntity = usersRepository.getOne(friendship.getFriendId());
        if(usersEntity != null) {
            friendship.setFriendName(usersEntity.getNickname());
        }
        return friendship;
    }
}
