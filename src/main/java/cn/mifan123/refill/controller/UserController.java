package cn.mifan123.refill.controller;

import cn.mifan123.refill.common.annotation.Auth;
import cn.mifan123.refill.common.annotation.CurrentUser;
import cn.mifan123.refill.common.constant.Constants;
import cn.mifan123.refill.common.exception.BusinessException;
import cn.mifan123.refill.common.vo.ApiFriendId;
import cn.mifan123.refill.common.vo.Friendship;
import cn.mifan123.refill.common.vo.User;
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
import java.util.Objects;

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
    @ApiOperation(value = "获取当前用户信息")
    @GetMapping("")
    public User get(@ApiIgnore @CurrentUser User user) {
        return user;
    }


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
    @PostMapping(value = "/friendships", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Friendship postFriendships(@ApiIgnore @CurrentUser Integer userId, @RequestBody ApiFriendId apiFriendId) {
        return null;
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation("删除好友")
    @DeleteMapping("/friendships/{id}")
    public void deleteFriendships(@ApiIgnore @CurrentUser Integer userId, @PathVariable("id") Integer id) {
        Friendship friendship = friendshipService.findOne(id);
        if(!Objects.equals(friendship.getUserId(), userId)) {
            throw new BusinessException("不能删除不属于自己的好友关系");
        }
        friendshipService.delete(id);
    }

}
