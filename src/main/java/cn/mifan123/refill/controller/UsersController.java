package cn.mifan123.refill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(value= "用户", description="用户相关API")
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {


    @ApiOperation(value = "用户信息")
    @GetMapping("/{userId}")
    public Integer list( @PathVariable("userId") Integer userId) {
        return userId;
    }

}
