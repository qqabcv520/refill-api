package cn.mifan123.refill.common.annotation;

import java.lang.annotation.*;


/**
 * 获取当前用户信息
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}