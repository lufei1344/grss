package com.grss.jlsx.api.process.util;

import java.util.Properties;

import com.grss.jlsx.core.utils.PropertiesUtil;

public class ParsePropertiesUtil {
	private static PropertiesUtil propertiesUtil = PropertiesUtil.getInstance();
	private static Properties properties = null;
	private static final String MESSAGE_PRO_FILE_NAME = "/messages_zh_CN";
	private static final String REST_IM_PRO_FILE_NAME = "/rest-IM";
	/**
	 * 文件上传地址根目录
	 */
	private static final String UPLOAD_PARENT_URL = "parent_url";
	/**
	 * 图片上传地址
	 */
	private static final String IMG_URL = "user_img";
	/**
	 * 配置文件上传地址
	 */
	private static final String PRO_URL = "pro_url";
	/**
	 * 文件上传地址
	 */
	private static final String FILE_URL = "file_url";
	/**
	 * 文件上传配置文件名称
	 */
	private static final String PRO_PULOAD_ADDRESS_NAME = "/upload_address";
	
	private static final String SERVICE_URL = "service_url";
	
	private static final String USER_PHOTO_ADDRESS = "user_photo_address";
	/**
	 * 
	 * @comment 获取消息信息
	 * @param key
	 * @return
	 * 2015年12月18日
	 * 下午2:39:45
	 */
	public static String getMessage(String key){
		properties = propertiesUtil.load(MESSAGE_PRO_FILE_NAME);
		return properties.getProperty(key);
	}
	
	public static String getIMConfig(String key){
		properties = propertiesUtil.load(REST_IM_PRO_FILE_NAME);
		return properties.getProperty(key);
	}
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
	 * @comment 获取图片上传目录
	 * @return
	 * 2015年11月2日
	 * 下午2:56:05
	 */
	public static String getImgAddress(){
		return propertiesUtil.load(PRO_PULOAD_ADDRESS_NAME).getProperty(IMG_URL);
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
	 * @comment 获取配置文件上传路径
	 * @return
	 * 2015年11月13日
	 * 下午4:43:55
	 */
	public static String getProFileAddress(){
		return propertiesUtil.load(PRO_PULOAD_ADDRESS_NAME).getProperty(PRO_URL);
	}
	/**
	 * 
	 * @comment 图片上传目录
	 * @return
	 * 2015年11月2日
	 * 下午2:56:29
	 */
	public static String getUploadImgAddress(){
		return getParentAddress() + getImgAddress();
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
	 * @comment 配置文件上传目录
	 * @return
	 * 2015年11月13日
	 * 下午4:43:29
	 */
	public static String getUploadProFileAddress(){
		return getParentAddress() + getProFileAddress();
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
	
}
