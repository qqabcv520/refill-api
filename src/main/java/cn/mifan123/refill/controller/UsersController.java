package cn.mifan123.refill.controller;

import cn.mifan123.refill.common.vo.Token;
import cn.mifan123.refill.common.vo.User;
import cn.mifan123.refill.common.vo.UserAuth;
import cn.mifan123.refill.service.EncryptService;
import cn.mifan123.refill.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

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
    @PostMapping(value = "/token", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Token postToken(@RequestBody UserAuth userAuth) {
        String token = usersService.tokenWithUsername(userAuth.getUsername(), userAuth.getPassword());
        log.info("用户登录：" + userAuth.getUsername());
        return new Token(token);
    }

    @ApiOperation(value = "获取用户信息")
    @GetMapping(value = "/{id}")
    public User get(@PathVariable("id") Integer id) {
        return usersService.findOne(id);
    }


}
