package cn.mifan123.refill.controller;

import cn.mifan123.refill.common.annotation.Auth;
import cn.mifan123.refill.common.annotation.CurrentUser;
import cn.mifan123.refill.common.constant.Constants;
import cn.mifan123.refill.common.vo.ApiFriendId;
import cn.mifan123.refill.common.vo.Friendship;
import cn.mifan123.refill.service.FriendshipService;
import cn.mifan123.refill.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Api(value= "当前登录用户", description="当前登录用户相关API")
@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Resource
    private UsersService usersService;
    @Resource
    private FriendshipService friendshipService;


    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation("获取用户好友列表")
    @GetMapping("/friendships")
    public List<Friendship> getFriendships(@ApiIgnore @CurrentUser Integer userId) {
        return friendshipService.findAllByUserId(userId, null, null);
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation("添加好友")
    @PostMapping("/friendships")
    public Friendship postFriendships(@ApiIgnore @CurrentUser Integer userId, @RequestBody ApiFriendId apiFriendId) {
        return null;
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation("删除好友")
    @DeleteMapping("/friendships/{id}")
    public void deleteFriendships(@ApiIgnore @CurrentUser Integer userId, @PathVariable("id") String id) {
        return;
    }

}
