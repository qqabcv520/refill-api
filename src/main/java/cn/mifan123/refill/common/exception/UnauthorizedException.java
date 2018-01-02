package cn.mifan123.refill.common.exception;

public class UnauthorizedException extends BusinessException {


    public UnauthorizedException(String msg) {
        super(401, msg);
    }
}
