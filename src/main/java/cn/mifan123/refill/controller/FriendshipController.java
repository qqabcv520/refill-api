package cn.mifan123.refill.controller;


import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value= "好友", description="好友相关API")
@RestController
@RequestMapping(value = "friendships", produces = MediaType.APPLICATION_JSON_VALUE)
public class FriendshipController {

//    @ApiOperation(value = "获取用户好友列表")
//    @Transactional
//    @GetMapping(value = "/{id}")
//    @Auth
//    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
//    public User get(@ApiIgnore @CurrentUser User user, @PathVariable("id") Integer id) {
//        if(!Objects.equals(user.getId(), id)) {
//            throw new BusinessException("无权限访问该用户");
//        }
//        return user;
//    }
}
