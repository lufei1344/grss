package com.grss.jlsx.core.utils;

import java.util.Random;

import com.grss.jlsx.core.utils.SecurityUtil;

public class WXUtil {
	
	public static String getNonceStr() {
		Random random = new Random();
		return SecurityUtil.MD5Encode(String.valueOf(random.nextInt(10000)), "GBK");
	}

	public static String getTimeStamp() {
		return String.valueOf(DateUtil.getTimestampTen());
	}
}
