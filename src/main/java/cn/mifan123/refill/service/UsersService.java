package cn.mifan123.refill.service;

import cn.mifan123.refill.common.vo.User;

public interface UsersService extends EntityService<User, Integer> {
    /**
     * 获取token
     * @param username
     * @param password
     * @return
     */
    String tokenWithUsername(String username, String password);

    /**
     * 根据用户名获User
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 通过用户名注册
     * @param username
     * @param password
     */
    User registerByUsername(String username, String password);
}
