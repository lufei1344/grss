package com.grss.jlsx.core.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class DateUtil {
	public static final String PATTERN_STANDARD = "yyyy-MM-dd HH:mm:ss";

	public static final String PATTERN_DATE = "yyyy-MM-dd";

	public static final String[] constellationArr = { "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座",
			"天蝎座", "射手座", "魔羯座" };

	public static final int[] constellationEdgeDay = { 20, 19, 21, 21, 21, 22, 23, 23, 23, 23, 22, 22 };

	/** 年月日时分秒(无下划线) yyyyMMddHHmmss */
	public static final String dtLong = "yyyyMMddHHmmss";

	/** 完整时间 yyyy-MM-dd HH:mm:ss */
	public static final String simple = "yyyy-MM-dd HH:mm:ss";

	/** 年月日(无下划线) yyyyMMdd */
	public static final String dtShort = "yyyyMMdd";

	/**
	 * 按照yyyy-MM-dd HH:mm:ss的格式，日期转字符串
	 * 
	 * @param date
	 * @return yyyy-MM-dd HH:mm:ss
	 */
	public static String date2Str(Date date) {
		return date2Str(date, PATTERN_DATE);
	}

	/**
	 * 按照参数format的格式，日期转字符串
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String date2Str(Date date, String format) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} else {
			return "";
		}
	}

	/**
	 * String类型转换Date
	 * 
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
	 * @return 2015年11月2日 下午2:10:24
	 */
	public static int getMonth() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MONTH) + 1;
	}

	/**
	 * 
	 * @comment 获取天
	 * @return 2015年11月2日 下午2:10:34
	 */
	public static int getDay() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.DATE);
	}

	/**
	 * 
	 * @comment 获取年份
	 * @return 2015年11月2日 下午2:10:49
	 */
	public static int getYear() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 
	 * @comment 获取当前小时
	 * @return 2015年11月30日 下午4:47:59
	 */
	public static int getHour() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 
	 * @comment 获取当前分钟
	 * @return 2015年11月30日 下午4:49:02
	 */
	public static int getMinute() {
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.MINUTE);
	}

	public static long getStartDate() {
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
	 * @return 2015年12月18日 下午6:24:34
	 */
	public static String timestampToDateString(long timestamp) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_STANDARD);
		return dateFormat.format(new Date(timestamp * 1000));
	}

	/**
	 * 
	 * @comment 时间戳转时间
	 * @param timestamp
	 * @return 2015年12月18日 下午6:28:13
	 */
	public static Date timestampToDate(long timestamp) {
		return new Date(timestamp * 1000);
	}

	/**
	 * 
	 * @comment 获取10位时间戳
	 * @return 2015年12月18日 下午6:29:48
	 */
	public static long getTimestampTen() {
		return System.currentTimeMillis() / 1000;
	}

	public static long getTimestamp() {
		return System.currentTimeMillis();
	}

	/**
	 * 根据日期获取星座
	 * 
	 * @return
	 */
	public static String getConstellation(Date date) {
		if (date == null) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.DAY_OF_MONTH);
		if (day < constellationEdgeDay[month]) {
			month = month - 1;
		}
		if (month >= 0) {
			return constellationArr[month];
		}
		// default to return 魔羯
		return constellationArr[11];
	}

	/**
	 * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
	 * 
	 * @return 以yyyyMMddHHmmss为格式的当前系统时间
	 */
	public static String getOrderNum() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	/**
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static String getDateFormatter() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(simple);
		return df.format(date);
	}

	/**
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 * 
	 * @return
	 */
	public static String getDate() {
		Date date = new Date();
		DateFormat df = new SimpleDateFormat(dtShort);
		return df.format(date);
	}

	/**
	 * 产生随机的三位数
	 * 
	 * @return
	 */
	public static String getThree() {
		Random rad = new Random();
		return rad.nextInt(1000) + "";
	}

	public static void main(String[] args) {
		System.out.println(getTimestamp());
	}
}
