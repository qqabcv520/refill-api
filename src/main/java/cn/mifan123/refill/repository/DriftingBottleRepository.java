package cn.mifan123.refill.repository;

import cn.mifan123.refill.entity.DriftingBottleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DriftingBottleRepository extends JpaRepository<DriftingBottleEntity, Integer> {
    List<DriftingBottleEntity> findAllBySenderId(Integer senderId, Pageable pageable);
    List<DriftingBottleEntity> findAllByReceiverId(Integer receiverId, Pageable pageable);
}
