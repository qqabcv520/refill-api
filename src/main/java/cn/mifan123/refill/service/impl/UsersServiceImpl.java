package cn.mifan123.refill.service.impl;

import cn.mifan123.refill.common.vo.User;
import cn.mifan123.refill.po.UsersEntity;
import cn.mifan123.refill.repository.UsersRepository;
import cn.mifan123.refill.service.EncryptService;
import cn.mifan123.refill.service.UsersService;
import org.springframework.cache.Cache;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.annotation.Resource;
import java.util.Date;

public class UsersServiceImpl extends EntityServiceImpl<UsersEntity, Integer> implements UsersService {


    @Resource
    private UsersRepository usersRepository;


    @Resource
    private EncryptService encryptService;

    @Resource
    private Cache tokenCache;

    @Override
    public String tokenWithUsername (String username, String password) {


        UsersEntity usersEntity = usersRepository.findByUsername(username);
        //TODO 验证密码
        usersEntity.setLastLoginTime(new Date());//更新最后登录时间
        usersRepository.save(usersEntity);

        String token = encryptService.generateToken();
        tokenCache.put(token, PoToVo(usersEntity, User.class));//缓存token

        return token;
    }

    @Override
    public JpaRepository<UsersEntity, Integer> getEntityDao() {
        return usersRepository;
    }


//    public User getUserByStr(String str) {
//        User user = new User();
//        if(str.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*")) {//邮箱
//
//        } else {
//        }
//        return user;
//    }




}
