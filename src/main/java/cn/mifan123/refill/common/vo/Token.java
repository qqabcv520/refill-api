package cn.mifan123.refill.common.vo;

import lombok.Data;

@Data
public class Token {
    private String token;
    public Token(String token) {
        this.token = token;
    }
}