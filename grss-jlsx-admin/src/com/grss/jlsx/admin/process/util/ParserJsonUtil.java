package com.grss.jlsx.admin.process.util;

import com.alibaba.fastjson.JSONObject;

public class ParserJsonUtil {
	
	@SuppressWarnings("unchecked")
	public static <T> T getJson(String text,Class<T> cls){
		return (T) JSONObject.parse(text);
		
	}
}
