package com.grss.jlsx.api.process.constant;

import com.grss.jlsx.api.process.util.ParsePropertiesUtil;

/**
 * Constants
 * 
 * @author Lynch 2014-09-15
 *
 */
public interface Constants {

	// 超文本请求协议
	public static String API_HTTP_SCHEMA = ParsePropertiesUtil.getIMConfig("API_HTTP_SCHEMA");
	// 主机域名
	public static String API_SERVER_HOST = ParsePropertiesUtil.getIMConfig("API_SERVER_HOST");
	// 应用标识
	public static String APPKEY = ParsePropertiesUtil.getIMConfig("APPKEY");
	// 客户端ID
	public static String APP_CLIENT_ID = ParsePropertiesUtil.getIMConfig("APP_CLIENT_ID");
	// 获取密钥
	public static String APP_CLIENT_SECRET = ParsePropertiesUtil.getIMConfig("APP_CLIENT_SECRET");
	// 获取默认密码
	public static String DEFAULT_PASSWORD = ParsePropertiesUtil.getIMConfig("DEFAULT_PASSWORD");
}
