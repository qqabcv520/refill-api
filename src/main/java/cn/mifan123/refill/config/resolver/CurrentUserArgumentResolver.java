package cn.mifan123.refill.config.resolver;//package lol.mifan.myblog.resolver;
//
//import lol.mifan.myblog.po.Users;
//import lol.mifan.myblog.service.UserService;
//import lol.mifan.myblog.util.annitation.CurrentUser;
//import org.springframework.core.MethodParameter;
//import org.springframework.web.bind.support.WebDataBinderFactory;
//import org.springframework.web.context.request.NativeWebRequest;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.method.support.ModelAndViewContainer;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by 米饭 on 2017-05-27.
// */
//public class CurrentUserArgumentResolver implements HandlerMethodArgumentResolver {
//
//    @Resource
//    private UserService userService;
//
//
//    @Override
//    public boolean supportsParameter(MethodParameter methodParameter) {
//        CurrentUser requestBean = methodParameter.getParameterAnnotation(CurrentUser.class);
//
//        return requestBean != null && Users.class.isAssignableFrom(methodParameter.getParameterType());
//    }
//
//    @Override
//    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
//        // 判断controller方法参数有没有写当前用户，如果有，这里返回即可
//        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
//        String token = request.getHeader("token");
//        return userService.findByToken(token);
//    }
//}
