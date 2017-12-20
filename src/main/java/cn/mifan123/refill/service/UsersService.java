package cn.mifan123.refill.service;

public interface UsersService {
    /**
     * 获取token
     * @param username
     * @param password
     * @return
     */
    String tokenWithUsername(String username, String password);

}
