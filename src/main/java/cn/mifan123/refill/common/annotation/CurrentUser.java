package cn.mifan123.refill.common.annotation;

import java.lang.annotation.*;


/**
 * 获取当前用户信息
 * 添加到controller方法参数上
 * 支持<code>Integer</code>和<code>cn.mifan123.refill.common.vo.User</code>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentUser {
}