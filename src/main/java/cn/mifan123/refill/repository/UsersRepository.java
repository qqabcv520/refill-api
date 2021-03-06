package cn.mifan123.refill.repository;

import cn.mifan123.refill.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
    UsersEntity findByUsername(String username);
}
