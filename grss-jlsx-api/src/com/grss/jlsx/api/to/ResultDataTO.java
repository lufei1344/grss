package com.grss.jlsx.api.to;

import java.util.ArrayList;
import java.util.List;
/**
 * 传输对象
 * @author xuelong
 *
 */
public class ResultDataTO {
	private String code;
	private String message;
	private Object result;
	public ResultDataTO(String code, String message, Object result) {
		super();
		this.code = code;
		this.message = message;
		this.result = result;
	}
	public ResultDataTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
	
	public ResultDataTO(String message) {
		super();
		this.code = "0";
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getResult() {
		return result == null ? new ArrayList<>() : result;
	}
	public void setResult(Object result) {
		this.result = result;
	}
	
	
	
	
}
