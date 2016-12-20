package com.grss.jlsx.core.network.sms;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.grss.jlsx.core.utils.SecurityUtil;


/**
 * 
 * @author 发送短信验证码
 *
 */
public class SmsChannelYoungLion extends SmsChannel {

	private static Logger logger = LoggerFactory.getLogger(SmsChannelYoungLion.class);
	private static Map<String,String> para = new HashMap<String,String>();
	static {
		para.put("username", "bjznkj");
		para.put("pwd", SecurityUtil.MD5("sQNTkYCy"));
		para.put("isUrlEncode", "no");
		para.put("charSetStr", "utf");
	}
	private static String MOBILE_KEY = "p";
	private static String CONTENT_KEY = "msg";
	private static String SMS_TEMPLATE = "【教练随行】您本次的验证码为：[code]请尽快进行验证。";
	private static String url= "http://api.app2e.com/smsBigSend.api.php";
	
	@Override
	public boolean sendValidateCode(String phone, String code) {
		if (StringUtils.isEmpty(phone)) {
			logger.error("发送的请求手机号为空");
			return false;
		}
		String message = StringUtils.replace(SMS_TEMPLATE,"[code]",code);
		para.put(CONTENT_KEY, message);
		para.put(MOBILE_KEY, phone);
		return SendRequestForPost(url, para);

	}


	public static void main(String[] args) {
		String className = "com.grss.jlsx.core.network.sms.SmsChannelYoungLion";

		try {
			SmsChannel obj = (SmsChannel)(Class.forName(className).newInstance());
			if (obj.sendValidateCode("18513833254", "123456")){
				System.out.println("发送成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}   
	}


}
