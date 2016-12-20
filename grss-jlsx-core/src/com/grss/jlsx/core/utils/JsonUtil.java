package com.grss.jlsx.core.utils;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class JsonUtil {
	
	/**
	 * 
	 * @param text 解析json
	 * @param params
	 * @return
	 */
	public static List<String> dissectJson(String text,String[] params){
		try{
			List<String> list = new ArrayList<>();
			if(params != null){
				JSONObject jo = JSON.parseObject(text);
				for(String key : params){
					list = JSON.parseArray(jo.getString(key), String.class);
				}
			}
			return list;
		}catch(Exception e){
			return null;
		}
	}
	
	public static String getJsonValue(String rescontent, String key) {
		JSONObject jsonObject;
		String v = null;
		try {
			jsonObject = JSON.parseObject(rescontent);
			v = jsonObject.getString(key);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return v;
	}

}
