package cn.mifan123.refill.controller;

import cn.mifan123.refill.common.annotation.Auth;
import cn.mifan123.refill.common.annotation.CurrentUser;
import cn.mifan123.refill.common.constant.Constants;
import cn.mifan123.refill.common.constant.enums.DriftingBottleState;
import cn.mifan123.refill.common.exception.BusinessException;
import cn.mifan123.refill.common.vo.DriftingBottle;
import cn.mifan123.refill.common.vo.DriftingBottleContent;
import cn.mifan123.refill.service.DriftingBottleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
        driftingBottle.setState(DriftingBottleState.NOT_PICK_UP.getCode());  //暂用0表示刚扔出的瓶子状态
        driftingBottle.setSendTime(new Date());
        driftingBottle = driftingBottleService.save(driftingBottle);
        return driftingBottle;
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation(value = "获取我扔出的瓶子")
    @GetMapping(value = "/senders")
    public List<DriftingBottle> getSenders(@ApiIgnore @CurrentUser Integer senderId,
                                           @ApiParam("页码，从0开始")@RequestParam(required = false) Integer page,
                                           @ApiParam("页大小")@RequestParam(required = false) Integer size,
                                           HttpServletResponse response) {
        response.setHeader("Total", String.valueOf(driftingBottleService.count()));
        return driftingBottleService.findAllBySenderId(senderId, page, size);
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation(value = "获取我收到的瓶子")
    @GetMapping(value = "/receivers")
    public List<DriftingBottle> getReceivers(@ApiIgnore @CurrentUser Integer receiverId,
                                             @ApiParam("页码，从0开始")@RequestParam(required = false) Integer page,
                                             @ApiParam("页大小")@RequestParam(required = false) Integer size,
                                             HttpServletResponse response) {
        response.setHeader("Total", String.valueOf(driftingBottleService.count()));
        return driftingBottleService.findAllByReceiverId(receiverId, page, size);
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation(value = "捞瓶子")
    @GetMapping(value = "")
    public DriftingBottle get(@ApiIgnore @CurrentUser Integer senderId) {
        DriftingBottle driftingBottle = driftingBottleService.findByRand(DriftingBottleState.NOT_PICK_UP.getCode(), senderId);
        if(driftingBottle == null) {
            return null;
        }else {
            driftingBottle.setState(DriftingBottleState.PICK_UP.getCode());
            return driftingBottleService.save(driftingBottle);
        }
    }

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation(value = "更改瓶子的状态")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public DriftingBottle patchState(@ApiIgnore @CurrentUser Integer receiverId,
                                   @PathVariable("id") Integer id,
                                   @RequestBody cn.mifan123.refill.common.vo.DriftingBottleState driftingBottleState) {

        Integer state = driftingBottleState.getState();
        DriftingBottle driftingBottle = driftingBottleService.findOne(id);
        DriftingBottle returnDriftingBottle = null;

        switch(DriftingBottleState.valueOf(driftingBottle.getState())) {
            case NOT_PICK_UP: {
                if (DriftingBottleState.PICK_UP.getCode() == state) {
                    driftingBottle.setState(DriftingBottleState.PICK_UP.getCode());
                    returnDriftingBottle = driftingBottleService.save(driftingBottle);
                } else {
                    throw new BusinessException("未捞起的瓶子只能转变为已捞起");
                }
                break;
            }

            case PICK_UP: {
                if (DriftingBottleState.NOT_PICK_UP.getCode() == state) {
                    driftingBottle.setState(DriftingBottleState.NOT_PICK_UP.getCode());
                    returnDriftingBottle = driftingBottleService.save(driftingBottle);
                } else if (DriftingBottleState.REPLY_UP.getCode() == state) {
                    driftingBottle.setReceiverId(receiverId);
                    driftingBottle.setReceiveTime(new Date());
                    driftingBottle.setState(DriftingBottleState.REPLY_UP.getCode());
                    returnDriftingBottle = driftingBottleService.save(driftingBottle);
                } else {
                    throw new BusinessException("已捞起的瓶子只能转变为未捞起或已回复");
                }
                break;
            }

            case REPLY_UP: {
                if(DriftingBottleState.DELETE_UP.getCode() == state) {
                    driftingBottle.setState(DriftingBottleState.DELETE_UP.getCode());
                    returnDriftingBottle = driftingBottleService.save(driftingBottle);
                }else {
                    throw new BusinessException("已捞起的瓶子只能转变为已删除");
                }
                break;
            }

            case DELETE_UP: {
                throw new BusinessException("已删除的瓶子不能转变");
            }

            default: {
                throw new BusinessException("数据库drifting-bottle表state字段数据错误");
            }

        }

        return returnDriftingBottle;
    }
}
