package cn.mifan123.refill.repository;

import cn.mifan123.refill.entity.DriftingBottleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DriftingBottleRepository extends JpaRepository<DriftingBottleEntity, Integer> {
    List<DriftingBottleEntity> findAllBySenderId(Integer senderId, Pageable pageable);
    List<DriftingBottleEntity> findAllByReceiverIdAndStateIsNot(Integer receiverId, Integer state, Pageable pageable);
    @Query(value = "select * from drifting_bottle " +
            "where state = ?1 " +
            "and sender_id != ?2 " +
            "and id >= " +
            "(select floor(" +
            "rand() *" +
            "((select max(id) from drifting_bottle) - (select min(id) from drifting_bottle) + 1) +" +
            "(select min(id) from drifting_bottle)" +
            "))" +
            "limit 1;",
            nativeQuery = true)
    DriftingBottleEntity findByRand(Integer state, Integer senderId);
}
