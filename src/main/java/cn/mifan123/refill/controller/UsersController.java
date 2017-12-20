package cn.mifan123.refill.controller;

import cn.mifan123.refill.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


@Api(value= "用户", description="用户相关API")
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    @Resource
    private UsersService usersService;
//    private Logger logger = ;


    @ApiOperation(value = "用户信息")
    @GetMapping("/{userId}")
    public Integer list( @PathVariable("userId") Integer userId) {
        return userId;
    }


    @Transactional
    @PostMapping(value = "/token", consumes="application/json")
    public Map<String, Object> postToken(@RequestBody User loginUser) {

        String token = usersService.tokenWithUsername(loginUser.username, loginUser.password);




//        logger.info("用户" + username + "登录，登录成功");

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        return map;
    }
    private static class User {
        String username;
        String password;
    }


}
