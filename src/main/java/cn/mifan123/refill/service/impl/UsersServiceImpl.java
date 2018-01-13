package cn.mifan123.refill.service.impl;

import cn.mifan123.refill.common.exception.BusinessException;
import cn.mifan123.refill.common.exception.UnauthorizedException;
import cn.mifan123.refill.common.vo.User;
import cn.mifan123.refill.entity.UsersEntity;
import cn.mifan123.refill.repository.UsersRepository;
import cn.mifan123.refill.service.EncryptService;
import cn.mifan123.refill.service.IMService;
import cn.mifan123.refill.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;

@Slf4j
@Service
public class UsersServiceImpl extends EntityServiceImpl<UsersEntity, User, Integer> implements UsersService {


    @Resource
    private UsersRepository usersRepository;


    @Resource
    private EncryptService encryptService;

    @Resource
    private IMService imService;

    @Override
    @Transactional
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
    public User findByUsername(String username) {
        return poToVo(usersRepository.findByUsername(username));
    }

    @Transactional
    @Override
    public User registerByUsername(String username, String password) {
        if (usersRepository.findByUsername(username) != null) {
            throw new BusinessException("用户名已存在:" +  username);
        }

        UsersEntity user = new UsersEntity();
        user.setUsername(username);
        user.setPassword(encryptService.encryptPassword(password, username));
        user.setCreateTime(new Date());
        user.setNickname(user.getUsername());
        user = usersRepository.save(user);

        imService.registerUsers(username, password);

        log.info("用户注册：" + username);

        return poToVo(user);
    }


    @Override
    public JpaRepository<UsersEntity, Integer> getJpaRepository() {
        return usersRepository;
    }


    @Override
    protected User poToVo(UsersEntity po) {
        User user =  super.poToVo(po);
        user.setPassword(null);
        return user;
    }
}
