package com.grss.jlsx.api.process.filter;

import com.alibaba.fastjson.serializer.ValueFilter;

public class HandleValueFilter implements ValueFilter{
	//private String[] ignoreNames;
	@Override
	public Object process(Object source, String name, Object value) {
		if(value == null){
			return "";
		}
		return value;
	}
	
}
