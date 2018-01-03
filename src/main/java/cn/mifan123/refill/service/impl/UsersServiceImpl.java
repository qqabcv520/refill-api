package cn.mifan123.refill.service.impl;

import cn.mifan123.refill.common.exception.UnauthorizedException;
import cn.mifan123.refill.common.vo.User;
import cn.mifan123.refill.entity.UsersEntity;
import cn.mifan123.refill.repository.UsersRepository;
import cn.mifan123.refill.service.EncryptService;
import cn.mifan123.refill.service.UsersService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class UsersServiceImpl extends EntityServiceImpl<UsersEntity, User, Integer> implements UsersService {


    @Resource
    private UsersRepository usersRepository;


    @Resource
    private EncryptService encryptService;


    @Override
    public String tokenWithUsername (String username, String password) {

        UsersEntity usersEntity = usersRepository.findByUsername(username);
        if (usersEntity == null ) {
            throw new UnauthorizedException("用户名不存在");
        }
        if(StringUtils.isEmpty(password)) {
            throw new UnauthorizedException("密码不能为空");
        }
        if(!encryptService.encryptPassword(password, username).equals(usersEntity.getPassword())) {
            throw new UnauthorizedException("密码错误");
        }
        usersEntity.setLastLoginTime(new Date());//更新最后登录时间
        usersRepository.save(usersEntity);
        return encryptService.generateToken(usersEntity.getId());
    }


    @Override
    public JpaRepository<UsersEntity, Integer> getJpaRepository() {
        return usersRepository;
    }
}
