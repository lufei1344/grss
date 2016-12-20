package com.grss.jlsx.api.process.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";

	public static final String PATTERN_DATE = "yyyy-MM-dd";
	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date){
		return date2Str(date,PATTERN_DATE);
	}
	
	/**
	 * 按照参数format的格式，日期转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date,String format){
		if(date!=null){
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		}else{
			return "";
		}
	}
	/**
	 * String类型转换Date
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date stringToDate(String strDate, String pattern) {
		if (strDate == null || strDate.equals("")) {
			throw new RuntimeException("str date null");
		}
		if (pattern == null || pattern.equals("")) {
			pattern = DateUtil.PATTERN_DATE;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Date date = null;

		try {
			date = sdf.parse(strDate);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
		return date;
	}
	/**
	 * 
	 * @comment 获取月份
	 * @return
	 * 2015年11月2日
	 * 下午2:10:24
	 */
	public static int getMonth(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}
	/**
	 * 
	 * @comment 获取天
	 * @return
	 * 2015年11月2日
	 * 下午2:10:34
	 */
	public static int getDay(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DATE);
	}
	/**
	 * 
	 * @comment 获取年份
	 * @return
	 * 2015年11月2日
	 * 下午2:10:49
	 */
	public static int getYear(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * 
	 * @comment 获取当前小时
	 * @return
	 * 2015年11月30日
	 * 下午4:47:59
	 */
	public static int getHour(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	/**
	 * 
	 * @comment 获取当前分钟
	 * @return
	 * 2015年11月30日
	 * 下午4:49:02
	 */
	public static int getMinute(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}
	public static long getStartDate(){
		  Calendar calEnviron = Calendar.getInstance();
		  // 每天的00:00.am开始执行
		  calEnviron.set(Calendar.HOUR_OF_DAY, 0);
		  calEnviron.set(Calendar.MINUTE, 00);
		  // date为制定时间
		  Date dateSetter = new Date();
		  dateSetter = calEnviron.getTime();
		  // nowDate为当前时间
		  Date nowDateSetter = new Date();
		  // 所得时间差为，距现在待触发时间的间隔
		  long intervalEnviron = dateSetter.getTime() - nowDateSetter.getTime();
		  if (intervalEnviron < 0) {
		   calEnviron.add(Calendar.DAY_OF_MONTH, 1);
		   dateSetter = calEnviron.getTime();
		   intervalEnviron = dateSetter.getTime() - nowDateSetter.getTime();
		  }
		  return intervalEnviron;
	}
	/**
	 * 
	 * @comment 时间戳转字符串
	 * @return
	 * 2015年12月18日
	 * 下午6:24:34
	 */
	public static String  timestampToDateString(long timestamp){
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_STANDARD);
		return dateFormat.format(new Date(timestamp * 1000));
	}
	/**
	 * 
	 * @comment 时间戳转时间
	 * @param timestamp
	 * @return
	 * 2015年12月18日
	 * 下午6:28:13
	 */
	public static Date timestampToDate(long timestamp){
		return new Date(timestamp * 1000);
	}
	/**
	 * 
	 * @comment 获取10位时间戳
	 * @return
	 * 2015年12月18日
	 * 下午6:29:48
	 */
	public static long getTimestamp(){
		return System.currentTimeMillis() / 1000;
	}
	
	/** 
	 * @comment 获取多少天前 或多少天后的日期 正数是当前日期加 负数是当前日期减
	 * @param a
	 * @return
	 * 2016年2月24日
	 * 上午9:42:01
	 */
	public static Date getAddOrSubtractDate(int a){
		Date nowDate=new Date();
		return new Date(nowDate.getTime()+(a*24*60*60*1000));
	}
}
