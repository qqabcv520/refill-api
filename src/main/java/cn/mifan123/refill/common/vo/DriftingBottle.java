package cn.mifan123.refill.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class DriftingBottle {
    private Integer id;
    private Integer senderId;
    private String senderName;
    private Integer receiverId;
    private String receiverName;
    private String content;
    private Date sendTime;
    private Date receiveTime;
    private Integer state;
}
