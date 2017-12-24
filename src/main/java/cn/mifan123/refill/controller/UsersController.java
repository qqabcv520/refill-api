package cn.mifan123.refill.controller;

import cn.mifan123.refill.common.vo.User;
import cn.mifan123.refill.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Api(value= "用户", description="用户相关API")
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    @Resource
    private UsersService usersService;
//    private Logger logger = ;


    @ApiOperation(value = "用户信息")
    @GetMapping("/{userId}")
    public User list(@PathVariable("userId") Integer userId) {
        return usersService.findOne(userId);
    }


    @ApiOperation(value = "登录并获取token")
    @Transactional
    @PostMapping(value = "/token", consumes="application/json")
    public String postToken(@RequestBody UserApi loginUser) {
        return usersService.tokenWithUsername(loginUser.username, loginUser.password);
    }
    private static class UserApi {
        String username;
        String password;
    }


}
