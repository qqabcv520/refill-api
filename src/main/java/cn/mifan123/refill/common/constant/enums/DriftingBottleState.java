package cn.mifan123.refill.common.constant.enums;

public enum DriftingBottleState {

    NOT_PICK_UP(0, "未捡起"), PICK_UP(1, "已捡起");


    private int code;
    private String message;

    DriftingBottleState(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static DriftingBottleState valueOf(int value) {
        for (DriftingBottleState v : values()) {
            if (v.getCode() == value) {
                return v;
            }
        }
        return null;
    }


}
