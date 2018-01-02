package cn.mifan123.refill.controller;

import cn.mifan123.refill.common.annotation.Auth;
import cn.mifan123.refill.common.annotation.CurrentUser;
import cn.mifan123.refill.common.constant.Constants;
import cn.mifan123.refill.common.exception.BusinessException;
import cn.mifan123.refill.common.vo.Token;
import cn.mifan123.refill.common.vo.User;
import cn.mifan123.refill.common.vo.UserAuth;
import cn.mifan123.refill.service.EncryptService;
import cn.mifan123.refill.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Objects;

@Slf4j
@Api(value= "用户", description="用户相关API")
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    @Resource
    private UsersService usersService;

    @Resource
    private EncryptService encryptService;


    @ApiOperation(value = "用户注册")
    @PostMapping(value = "")
    public User post(@RequestBody UserAuth userAuth){
        User user = new User();
        user.setUsername(userAuth.getUsername());
        user.setPassword(encryptService.encryptPassword(userAuth.getPassword(), userAuth.getUsername()));
        user = usersService.save(user);
        log.info("用户注册：" + userAuth.getUsername());
        return user;
    }

    @ApiOperation(value = "登录并获取token")
    @Transactional
    @PostMapping(value = "/token", consumes="application/json")
    public Token postToken(@RequestBody UserAuth userAuth) {
        String token = usersService.tokenWithUsername(userAuth.getUsername(), userAuth.getPassword());
        log.info("用户登录：" + userAuth.getUsername());
        return new Token(token);
    }

    @ApiOperation(value = "获取用户信息")
    @Transactional
    @GetMapping(value = "/{id}")
    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    public User get(@ApiIgnore @CurrentUser User user, @PathVariable("id") Integer id) {
        if(!Objects.equals(user.getId(), id)) {
            throw new BusinessException("无权限访问该用户");
        }
        return user;
    }



}
