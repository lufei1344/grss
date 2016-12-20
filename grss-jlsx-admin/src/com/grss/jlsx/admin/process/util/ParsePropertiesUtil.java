package com.grss.jlsx.admin.process.util;

import com.grss.jlsx.core.utils.PropertiesUtil;

public class ParsePropertiesUtil {
	private static PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
	/**
	 * 文件上传地址根目录
	 */
	private static final String UPLOAD_PARENT_URL = "parent_url";
	/**
	 * 图片上传地址
	 */
	private static final String IMG_URL = "imgage";
	/**
	 * 文件上传地址
	 */
	private static final String FILE_URL = "file_url";
	/**
	 * 文件上传配置文件名称
	 */
	private static final String PRO_PULOAD_ADDRESS_NAME = "/upload";
	
	private static final String SERVICE_URL = "service_url";
	
	private static final String PRO_MAP_NAME = "/map";
	
	/**
	 * 
	 * @comment 获取上传根目录
	 * @return
	 * 2015年11月2日
	 * 上午9:57:15
	 */
	public static String getParentAddress(){
		return propertiesUtil.load(PRO_PULOAD_ADDRESS_NAME).getProperty(UPLOAD_PARENT_URL);
	}
	/**
	 * 
	 * @comment 获取文件上传路径
	 * @return
	 * 2015年11月3日
	 * 上午11:48:22
	 */
	public static String getFileAddress(){
		return propertiesUtil.load(PRO_PULOAD_ADDRESS_NAME).getProperty(FILE_URL);
	}
	
	/**
	 * 
	 * @comment 文件上传目录
	 * @return
	 * 2015年11月3日
	 * 上午11:49:26
	 */
	public static String getUploadFileAddress(){
		return getParentAddress() + getFileAddress();
	}
	
	/**
	 * 
	 * @comment 获取静态资源服务器地址
	 * @return
	 * 2015年11月2日
	 * 下午1:42:06
	 */
	public static String getServiceAddress(){
		return propertiesUtil.load(PRO_PULOAD_ADDRESS_NAME).getProperty(SERVICE_URL);
	}
	
	public static String getKey(String key){
		return propertiesUtil.load(PRO_PULOAD_ADDRESS_NAME).getProperty(key);
	}
	/**
	 * 上传图片
	 * @return
	 */
	public static String getUploadImgAddress(){
		return propertiesUtil.load(PRO_PULOAD_ADDRESS_NAME).getProperty(IMG_URL);
	}
	
	public static String getMapKey(String key){
		return propertiesUtil.load(PRO_MAP_NAME).getProperty(key);
	}
}
