package cn.mifan123.refill.common.annotation;


import java.lang.annotation.*;

/**
 * 添加到Controller方法上，限制这个方法需要token才能访问
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Auth {

}
