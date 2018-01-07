package cn.mifan123.refill.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class User {

    private Integer id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Date createTime;
    private Date lastLoginTime;
    private String avatar;
}
