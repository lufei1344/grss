package com.grss.jlsx.api.process.interceptor;

import java.util.Map;

import com.grss.jlsx.api.process.constant.SecurityConstant;
import com.grss.jlsx.api.process.util.ParsePropertiesUtil;
import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.service.GrssUserService;
import com.grss.jlsx.core.utils.SecurityUtil;

public class Handle {
	/**
	 * 
	 * @comment 拦截没有权限访问的请求
	 * @return
	 * 2015年12月16日
	 * 下午2:10:35
	 */
	public static String HoldUp(Map<String, String[]> params,GrssUserService grssUserService){
		String token = ((String[]) params.get(SecurityConstant.TOKEN))[0];
		if(params != null && (token != null && !"".equals(token))){
			return checkToken(token, grssUserService);
		}
		return ParsePropertiesUtil.getMessage("message.token.lose");
	}
	/**
	 * 
	 * @comment 检查token
	 * @param token
	 * @param userService
	 * @return
	 * 2015年12月21日
	 * 上午9:36:04
	 */
	public static String checkToken(String token,GrssUserService grssUserService){
		boolean isSuccess = false;
		String[] unToken = SecurityUtil.unToken(token);
		String uid = unToken[0];
		if(uid != null && !"".equals(uid)){
			GrssUser user = grssUserService.findGrssUserByUserId(uid);
			if(SecurityUtil.token(uid, user.getPassword()).equals(token) || SecurityUtil.token(uid, user.getPassword()).replace("|", SecurityUtil.TRANSLATION).equals(token)){
				isSuccess  = true;
			}
		}
		
		if(!isSuccess){
			return  ParsePropertiesUtil.getMessage("message.token.invalid");
		}
		return null;
	}
}
