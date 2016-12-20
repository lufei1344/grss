package com.grss.jlsx.api.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.grss.jlsx.api.process.constant.Constants;
import com.grss.jlsx.api.process.constant.EndPoints;
import com.grss.jlsx.api.process.filter.HandleValueFilter;
import com.grss.jlsx.api.process.util.ParsePropertiesUtil;
import com.grss.jlsx.api.to.ResultDataTO;
import com.grss.jlsx.core.prove.ClientSecretCredential;
import com.grss.jlsx.core.prove.Credential;

import roles.RolesConstants;

/**
 * Controller类基类
 * 
 * @ClassName: cn.com.yg.rest.spring.BaseController
 * @Description: 
 *               实现ApplicationContextAware接口。SpringMVC框架自动载入ApplicationContext对象实例
 * @author Ryan.wang
 * @date 2015-9-24 下午2:53:52
 */
public class BaseController implements ApplicationContextAware {
	@Resource
	private MessageSource messageSource;
	private ApplicationContext applicationContext;

	private static final String RESPONSE_SUCCESS_KEY = "success";
	private static final String RESPONSE_DATA_PROPERTY_KEY = "data";
	private static final String RESPONSE_ERROR_PROPERTY_KEY = "errors";
	protected Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID, Constants.APP_CLIENT_SECRET, RolesConstants.USER_ROLE_APPADMIN, EndPoints.TOKEN_APP_URL);
	//静态服务器地址
	protected static String serverPath=ParsePropertiesUtil.getServiceAddress();
	/**
	 * log日志
	 */
	protected Log log = LogFactory.getLog(this.getClass());

	/**
	 * 读取资源文件具体信息
	 * 
	 * @Title: getMessage
	 * @Description: Key-Value文本资源配置
	 * @param code
	 * @return String
	 * @throws
	 */
	public String getMessage(String code) {
		return messageSource.getMessage(code, null, getRequest().getLocale());
	}

	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	protected HttpSession getSession() {
		return getRequest().getSession();
	}

	/**
	 * 正常返回时，ModelMap对象
	 * 
	 * @Title: RetSuccessObject
	 * @Description: 返回标准的ModelMap
	 * @param data
	 * @return ModelMap
	 * @throws
	 */
	protected ModelMap RetSuccessObject(Object data) {
		return RetSuccessObject(data, RESPONSE_DATA_PROPERTY_KEY);
	}

	protected ModelMap RetSuccessObject(Object data, String key) {
		ModelMap m = new ModelMap();
		if (key == null || key.trim().length() == 0)
			key = RESPONSE_ERROR_PROPERTY_KEY;
		m.put(RESPONSE_SUCCESS_KEY, true);
		m.put(key, data);
		return m;
	}

	protected ModelMap RetFailedObject(String msg) {
		ModelMap m = new ModelMap();
		m.put(RESPONSE_SUCCESS_KEY, false);
		m.put(RESPONSE_ERROR_PROPERTY_KEY, msg);
		return m;
	}

	/**
	 * 返回具体的ModelMap
	 * 
	 * @Title: sendList
	 * @Description: 封装List<T>至ModelMap中
	 * @param d
	 * @return ModelMap
	 * @throws
	 */
	protected static <T> ModelMap sendList(List<T> d) {
		ModelMap map = new ModelMap();
		map.put(RESPONSE_SUCCESS_KEY, true);
		map.put(RESPONSE_DATA_PROPERTY_KEY, d);
		return map;
	}

	public String getSystemMessage(String code, Object[] args, String defaultValue) {
		return this.applicationContext.getMessage(code, args, defaultValue, Locale.CHINA);
	}

	public String getSystemMessage(String code, String defaultValue) {
		return getSystemMessage(code, null, defaultValue);
	}

	public String getSystemMessage(String code) {
		return getSystemMessage(code, null, "");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public ApplicationContext getContext() {
		return this.applicationContext;
	}
	
	/**
	 * 
	 * @comment 失败返回消息
	 * @param message
	 * @return
	 * 2015年12月18日
	 * 下午2:14:23
	 */
	protected ResultDataTO resultFaild(String message){
		return new ResultDataTO(message);
	}
	
	/**
	 * 
	 * @comment 成功返回消息
	 * @param message
	 * @param result
	 * @return
	 * 2015年12月18日
	 * 下午2:16:10
	 */
	protected ResultDataTO resultSuccess(String message,Object obj){
		return resultMessage("1", message, obj);
	}
	/**
	 * 
	 * @comment 返回消息
	 * @return
	 * 2015年12月23日
	 * 上午10:23:08
	 */
	protected ResultDataTO resultMessage(String code,String message,Object obj){
		List<Object> result = new ArrayList<>();
		if(obj != null){
			if(obj instanceof List)
				result.addAll((List<?>)obj);
			else
				result.add(obj);
		}
		HandleValueFilter filter = new HandleValueFilter();
		ResultDataTO dataTO  = new ResultDataTO(code,message,toJson(result,filter));
		return dataTO;
	}
	/**
	 * 
	 * @comment 转换json当需要特殊处理JSON格式时需要用到这个方法
	 * @param objValue
	 * @param filter
	 * @return
	 * 2015年12月24日
	 * 下午1:05:27
	 */
	protected Object toJson(Object objValue,SerializeFilter filter){
		return JSONObject.parse(JSON.toJSONString(objValue, filter),Feature.OrderedField);
	}
	
	
	/**
	 * 
	 * @comment 转换JSON
	 * @param objValue
	 * @return
	 * 2015年12月24日
	 * 下午1:06:43
	 */
	protected Object toJson(Object objValue){
		return JSON.toJSON(objValue);
	}
	
}
