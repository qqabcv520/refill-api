package cn.mifan123.refill.repository;

import cn.mifan123.refill.entity.FriendshipEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<FriendshipEntity, Integer> {
    List<FriendshipEntity> findAllByUserId(Integer userId, Pageable pageable);
}
