package com.grss.jlsx.api.process.interceptor;

import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.grss.jlsx.api.process.annotation.AuthorityAnnotation;
import com.grss.jlsx.api.process.spring.SpringApplicationContext;
import com.grss.jlsx.api.to.ResultDataTO;
import com.grss.jlsx.core.service.GrssUserService;
import com.grss.jlsx.core.utils.StringUtil;

public class RequestInterceptor extends SpringApplicationContext implements HandlerInterceptor{
	@Resource
	private GrssUserService grssUserService;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if(ex != null){
			StringBuilder builder = new StringBuilder(ex.toString() + "\r\n");
			StackTraceElement[] stackTrace = ex.getStackTrace();
			for(int i = 0; i < stackTrace.length; i++){
				if(i > 10){
					break;
				}
				StackTraceElement e = stackTrace[i];
				builder.append(e.getClassName() + "." + e.getMethodName() + "(" + e.getFileName() + ":" + e.getLineNumber() + ")\r\n");
			}
			log.error(builder);
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		boolean flag = true;
		Method method = handlerMethod.getMethod();
		Map<String, String[]> paramsMap = request.getParameterMap();
		SystemContextSetup.setPagerParams(paramsMap);
		AuthorityAnnotation annotation = method.getAnnotation(AuthorityAnnotation.class);
		if(annotation != null && !annotation.isApprove()){
			String message = Handle.HoldUp(paramsMap, grssUserService);
			if(!StringUtil.empty(message)){
				response.setContentType("text/html;charset=utf-8");
				PrintWriter writer = response.getWriter();
				writer.write(unlawfulVisit(message));
				flag = false;
			}
		}
		return flag;
	}
	/**
	 * 
	 * @comment 没有权限是提示信息
	 * @return
	 * 2015年12月18日
	 * 下午2:01:00
	 */
	private char[] unlawfulVisit(String message){
		return JSONObject.toJSONString(new ResultDataTO(message)).toCharArray();
	}

}
