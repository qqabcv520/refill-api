package cn.mifan123.refill.config;

import cn.mifan123.refill.config.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by 米饭 on 2017-05-26.
 */
@Configuration
public class SpringMvcConfig extends WebMvcConfigurerAdapter {


    @Bean
    HandlerInterceptor tokenInterceptor() {
        return new AuthInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor())
                .addPathPatterns("/api/**");
    }

    @Override//跨域
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTION");
//                .maxAge(3600);
    }

//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(currentUserArgumentResolver());
//    }
//
//    @Override
//    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
//        exceptionResolvers.add(exceptionResolver());
//    }
//
////    @Bean
////    public CurrentUserArgumentResolver currentUserArgumentResolver() {
////        return new CurrentUserArgumentResolver();
////    }
////
//    @Bean
//    public ExceptionResolver exceptionResolver() {
//        return new ExceptionResolver();
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 多个拦截器组成一个拦截器链
//        // addPathPatterns 用于添加拦截规则
//        // excludePathPatterns 用户排除拦截
//        registry.addInterceptor(new AuthInterceptor()).addPathPatterns("/**");
//        super.addInterceptors(registry);
//    }
}
