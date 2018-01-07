package cn.mifan123.refill.controller;


import cn.mifan123.refill.common.annotation.Auth;
import cn.mifan123.refill.common.annotation.CurrentUser;
import cn.mifan123.refill.common.constant.Constants;
import cn.mifan123.refill.common.exception.BusinessException;
import cn.mifan123.refill.common.vo.FriendId;
import cn.mifan123.refill.common.vo.Friendship;
import cn.mifan123.refill.service.FriendshipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Api(value= "好友", description="好友相关API")
@RestController
@RequestMapping(value = "friendships", produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendshipController {

    @Resource
    private FriendshipService friendshipService;

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation("获取用户好友列表")
    @GetMapping("")
    public List<Friendship> getFriendships(@ApiIgnore @CurrentUser Integer userId) {
        return friendshipService.findAllByUserId(userId, null, null);
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation("添加好友")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Friendship postFriendships(@ApiIgnore @CurrentUser Integer userId, @RequestBody FriendId apiFriendId) {
        Friendship friendship = new Friendship();
        friendship.setFriendId(apiFriendId.getFriendId());
        friendship.setUserId(userId);
        friendship.setIntimacy(0);
        friendship.setIntimacyToday(0);
        friendship.setLastInteractive(new Date());
        friendship.setCreateTime(new Date());
        friendshipService.save(friendship);
        return friendship;
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation("删除好友")
    @DeleteMapping("/{id}")
    public void deleteFriendships(@ApiIgnore @CurrentUser Integer userId, @PathVariable("id") Integer id) {
        Friendship friendship = friendshipService.findOne(id);
        if(!Objects.equals(friendship.getUserId(), userId)) {
            throw new BusinessException("不能删除不属于自己的好友关系");
        }
        friendshipService.delete(id);
    }

}
