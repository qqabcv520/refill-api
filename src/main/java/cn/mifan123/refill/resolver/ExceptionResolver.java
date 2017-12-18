/**   
 * filename：ExceptionHandler.java
 *   
 * date：2016年5月11日
 * Copyright reey Corporation 2016 
 *   
 */
package cn.mifan123.refill.resolver;

import cn.mifan123.refill.exception.HttpException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class ExceptionResolver implements HandlerExceptionResolver {

	
	private static final Logger logger = LoggerFactory.getLogger(ExceptionResolver.class);
	private static final ObjectMapper objectMapper = new ObjectMapper();
	
	
	
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response, Object handler, Exception e) {
		
		
		HandlerMethod method = (HandlerMethod)handler;

		response.setContentType("application/json;charset=UTF-8");
		HashMap<String, Object> map = new HashMap<>();
		if(e instanceof HttpException) {
			response.setStatus(((HttpException) e).getCode());
			map.put("error", e.getMessage());
		}  else if(e instanceof HttpRequestMethodNotSupportedException) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			map.put("error", "not found");
//			e.printStackTrace();
			logger.info("URI:" + request.getRequestURI() + ", Exception:" + e.toString());
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			map.put("error", "服务器异常");
			e.printStackTrace();
			logger.error("URI:" + request.getRequestURI() + ",Exception:" + e.toString());
		}

		try {
			response.getWriter().print(objectMapper.writeValueAsString(map));
		} catch (IOException e1) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			logger.error(e1.getMessage());
		}
		return new ModelAndView();
	}

}
