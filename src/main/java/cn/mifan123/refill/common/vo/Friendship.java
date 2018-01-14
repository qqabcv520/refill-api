package cn.mifan123.refill.common.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Friendship {
    @ApiModelProperty(value = "好友关系ID")
    private Integer id;
    @ApiModelProperty(value = "拥有该好友关系的用户ID")
    private Integer userId;
    @ApiModelProperty(value = "好友ID")
    private Integer friendId;
    @ApiModelProperty(value = "好友昵称")
    private String friendName;
    @ApiModelProperty(value = "总亲密度")
    private Integer intimacy;
    @ApiModelProperty(value = "今天增加的亲密度")
    private Integer intimacyToday;
    @ApiModelProperty(value = "最后一次亲密度增长时间")
    private Date lastInteractive;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
}
