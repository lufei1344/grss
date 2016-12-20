package com.grss.jlsx.core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/**
	 * 
	 * @comment 验证字符串是否为空
	 * @param value
	 * @return
	 * 2015年12月24日
	 * 下午12:57:50
	 */
	public static boolean empty(String value){
		return value == null || "".equals(value);
	}
	public static boolean empty(String[] values){
		if(values != null){
			for(String st : values){
				if(empty(st)){
					return true;
				}
			}
		}
		return false;
	}
	/**
	 * 
	 * @comment 消除前后空格
	 * @param value
	 * @return
	 * 2015年12月24日
	 * 下午2:33:36
	 */
	public static String trim(String value){
		if(!empty(value)){
			return value.trim();
		}
		return value;
	}
	/**
	 * 
	 * @comment UTF8转码
	 * @param s
	 * @return
	 * 2015年12月29日
	 * 下午12:29:09
	 */
	public static String encodingToUTF8(String s){
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @comment 获取UUID
	 * @return
	 * 2016年1月27日
	 * 下午2:43:14
	 */
	public static String getUUID(){
		return replaceAll(UUID.randomUUID().toString(),"-","");
	}
	
	/**
	 *  检查非法字符
	 * 
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);

		return matcher.lookingAt();
	}
	/**
	 * 替换
	 * @param value
	 * @param regex
	 * @param replacement
	 * @return
	 */
	public static String replaceAll(String value,String regex,String replacement){
		if(!empty(value)){
			return value.replaceAll(regex, replacement);
		}
		return null;
	}
	
	/**
	 * @comment 获取分类编号
	 * @param maxCode
	 * @return
	 * 2016年4月19日
	 * 下午2:48:47
	 */
	public static String getMaxCodeAdd(String maxCode,String parentCode){
		if(StringUtil.empty(maxCode)){
			return parentCode+"001";
		}
		int strLength=maxCode.length();
		maxCode=maxCode.replace("C","");
		Integer maxCodeNum=Integer.parseInt(maxCode);
		maxCodeNum++;
		String resultCode="C";
		if(strLength==4){
			if(maxCodeNum>0&&maxCodeNum<10)
				resultCode+="00"+maxCodeNum;
			else if(maxCodeNum>=10&&maxCodeNum<100)
				resultCode+="0"+maxCodeNum;
			else
				resultCode+=maxCodeNum;
		}
//		if(strLength==7){
//			if(maxCodeNum>=1000&&maxCodeNum<10000)
//				resultCode+="00"+maxCodeNum;
//			else if(maxCodeNum>=10000&&maxCodeNum<100000)
//				resultCode+="0"+maxCodeNum;
//			else
//				resultCode+=maxCodeNum;
//		}
		if(strLength==10){
			System.out.println();
			if(maxCodeNum>=1000000&&maxCodeNum<10000000)
				resultCode+="00"+maxCodeNum;
			else if(maxCodeNum>=10000000&&maxCodeNum<100000000)
				resultCode+="0"+maxCodeNum;
			else
				resultCode+=maxCodeNum;
		}
		return resultCode;
	}
	
	public static Integer getRandomNumber(Integer min,Integer max){
        min=min==null?1:min;
        max=max==null?100:max;
		Random random = new Random();
        int s = random.nextInt(max)%(max-min+1) + min;
        return s;
	}
}
