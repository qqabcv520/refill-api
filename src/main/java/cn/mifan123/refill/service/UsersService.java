package cn.mifan123.refill.service;

import cn.mifan123.refill.common.vo.User;
import cn.mifan123.refill.entity.UsersEntity;

public interface UsersService extends EntityService<UsersEntity, User, Integer> {
    /**
     * 获取token
     * @param username
     * @param password
     * @return
     */
    String tokenWithUsername(String username, String password);

}
