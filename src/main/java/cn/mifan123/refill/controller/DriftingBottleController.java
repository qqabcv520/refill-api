package cn.mifan123.refill.controller;

import cn.mifan123.refill.common.annotation.Auth;
import cn.mifan123.refill.common.annotation.CurrentUser;
import cn.mifan123.refill.common.constant.Constants;
import cn.mifan123.refill.common.vo.DriftingBottle;
import cn.mifan123.refill.common.vo.DriftingBottleContent;
import cn.mifan123.refill.common.vo.User;
import cn.mifan123.refill.service.DriftingBottleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.Date;

@Api(value= "漂流瓶", description="漂流瓶相关API")
@RestController
@RequestMapping(value = "/driftingbottles", produces = MediaType.APPLICATION_JSON_VALUE)
public class DriftingBottleController {

    @Resource
    private DriftingBottleService driftingBottleService;

    @Auth
    @ApiImplicitParam(value="令牌", paramType = "header", required = true, name = Constants.TOKEN_HEADER_NAME, dataType = "String")
    @ApiOperation(value = "扔瓶子")
    @PostMapping(value = "")
    public DriftingBottle post(@ApiIgnore @CurrentUser Integer userid, @RequestBody DriftingBottleContent driftingBottleContent) {
        DriftingBottle driftingBottle = new DriftingBottle();
        driftingBottle.setContent(driftingBottleContent.getContent());
        driftingBottle.setSenderId(userid);
        driftingBottle.setState(0);  //暂用0表示刚扔出的瓶子状态
        driftingBottle.setSendTime(new Date());
        driftingBottleService.save(driftingBottle);
        return driftingBottle;
    }

}
