package cn.mifan123.refill.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Friendship {
    private Integer id;
    private Integer userId;
    private Integer friendId;
    private String friendName;
    private Integer intimacy;
    private Integer intimacyToday;
    private Date lastInteractive;
    private Date createTime;
}
