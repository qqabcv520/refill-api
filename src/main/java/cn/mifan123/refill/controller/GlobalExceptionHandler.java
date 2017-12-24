package cn.mifan123.refill.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


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
        logger.error("URI:" + request.getRequestURI() + ",Exception:" + exception.toString());
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
        logger.error("URI:" + request.getRequestURI() + ",Exception:" + exception.toString());
        return map;
    }

}