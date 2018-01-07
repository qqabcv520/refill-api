package cn.mifan123.refill.controller;

import cn.mifan123.refill.common.annotation.Auth;
import cn.mifan123.refill.common.annotation.CurrentUser;
import cn.mifan123.refill.common.constant.Constants;
import cn.mifan123.refill.common.vo.DriftingBottle;
import cn.mifan123.refill.common.vo.DriftingBottleContent;
import cn.mifan123.refill.service.DriftingBottleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Api(value= "漂流瓶", description="漂流瓶相关API")
@RestController
@RequestMapping(value = "/drifting-bottles", produces = MediaType.APPLICATION_JSON_VALUE)
public class DriftingBottleController {

    @Resource
    private DriftingBottleService driftingBottleService;

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation(value = "扔瓶子")
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DriftingBottle post(@ApiIgnore @CurrentUser Integer userId, @RequestBody DriftingBottleContent driftingBottleContent) {
        DriftingBottle driftingBottle = new DriftingBottle();
        driftingBottle.setContent(driftingBottleContent.getContent());
        driftingBottle.setSenderId(userId);
        driftingBottle.setState(0);  //暂用0表示刚扔出的瓶子状态
        driftingBottle.setSendTime(new Date());
        driftingBottle = driftingBottleService.save(driftingBottle);
        return driftingBottle;
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation(value = "获取我扔出的瓶子")
    @GetMapping(value = "/senders/{page}/{size}")
    public List<DriftingBottle> getSenders(@ApiIgnore @CurrentUser Integer senderId, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return driftingBottleService.findAllBySenderId(senderId, page, size);
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation(value = "获取我收到的瓶子")
    @GetMapping(value = "/receivers/{page}/{size}")
    public List<DriftingBottle> getReceivers(@ApiIgnore @CurrentUser Integer receiverId, @PathVariable("page") Integer page, @PathVariable("size") Integer size) {
        return driftingBottleService.findAllByReceiverId(receiverId, page, size);
    }

}
