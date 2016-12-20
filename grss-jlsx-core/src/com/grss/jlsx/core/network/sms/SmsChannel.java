/**    
 * @Title: SmsChannel.java   
 * @Package cn.com.yg.notice  
 * @Description: TODO 
 * @author Ryan.wang    
 * @date 2015年12月29日 下午1:45:01  
 * @company 上海（奕阁）网络科技有限公司
 * @version V1.0    
 */
package com.grss.jlsx.core.network.sms;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.grss.jlsx.core.utils.RestClientUtil;

public abstract class SmsChannel {
	static Logger logger =LoggerFactory.getLogger(SmsChannel.class);
	public abstract boolean sendValidateCode(String phone ,String code);


	protected boolean SendRequestForPost(String sendUrl, Map<String, String> params) {
		boolean ret = false;
		try {
			String text = RestClientUtil.post(sendUrl, params);
			JSONObject jsonObject = JSON.parseObject(text);
			if(jsonObject != null && "100".equals(jsonObject.getString("status"))){
				ret = true;
			}
		} catch (Exception e) {
			ret = false;
			e.printStackTrace();
		}
		return ret;
	}

}
