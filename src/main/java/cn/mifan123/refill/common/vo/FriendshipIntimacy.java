package cn.mifan123.refill.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 亲密度
 */
@Data
public class FriendshipIntimacy {
    @ApiModelProperty(value = "今天增加的亲密度")
    private Integer todayIntimacy;
}
