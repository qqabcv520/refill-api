package cn.mifan123.refill.config.resolver;//package lol.mifan.myblog.resolver;

import cn.mifan123.refill.common.annotation.CurrentUser;
import cn.mifan123.refill.common.constant.Constants;
import cn.mifan123.refill.common.exception.BusinessException;
import cn.mifan123.refill.common.vo.User;
import cn.mifan123.refill.service.UsersService;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 米饭 on 2017-05-27.
 */
public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {


    @Resource
    private UsersService usersService;

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        CurrentUser requestBean = methodParameter.getParameterAnnotation(CurrentUser.class);

        return requestBean != null;
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        // 判断controller方法参数有没有写当前用户，如果有，这里返回即可
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Integer userId = (Integer) request.getAttribute(Constants.REQUEST_USER_KEY);
        if(userId == null) {
            throw new BusinessException(HttpServletResponse.SC_FORBIDDEN, "无TOKEN");
        } else if(Integer.class.isAssignableFrom(methodParameter.getParameterType())) {
            return userId;
        } else if(User.class.isAssignableFrom(methodParameter.getParameterType())) {
            return usersService.findOne(userId);
        }
        return null;
    }
}
