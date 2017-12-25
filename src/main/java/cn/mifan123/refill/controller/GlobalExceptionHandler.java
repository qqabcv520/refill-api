package cn.mifan123.refill.controller;

import cn.mifan123.refill.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * 所有异常报错
     */
    @ExceptionHandler
    public Map<String, Object> allExceptionHandler(HttpServletRequest request,
                                                   HttpServletResponse response,
                                                   Exception exception) {
        HashMap<String, Object> map = new HashMap<>();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        map.put("error", "服务器异常");
        exception.printStackTrace();
        log.error("URI:" + request.getRequestURI() + ",  Exception:" + exception.toString(), exception);
        return map;
    }

    /**
     * 请求方法不支持
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Map<String, Object> httpRequestMethodNotSupportedExceptionHandler(HttpServletRequest request,
                                                                             HttpServletResponse response,
                                                                             Exception exception) {
        HashMap<String, Object> map = new HashMap<>();
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        map.put("error", "请求方法不支持");
        log.warn("URI:" + request.getRequestURI() + ",  Exception:" + exception.toString());
        return map;
    }

    /**
     * 请求方法不支持
     */
    @ExceptionHandler(BusinessException.class)
    public Map<String, Object> businessExceptionHandler(HttpServletRequest request,
                                                        HttpServletResponse response,
                                                        Exception exception) {
        HashMap<String, Object> map = new HashMap<>();
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        map.put("error", "业务异常");
        log.warn("URI:" + request.getRequestURI() + ",  Exception:" + exception.toString());
        return map;
    }



}