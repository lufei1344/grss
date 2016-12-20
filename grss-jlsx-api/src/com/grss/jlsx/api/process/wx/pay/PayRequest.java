package com.grss.jlsx.api.process.wx.pay;

import java.util.Map;

import com.grss.jlsx.core.utils.RestClientUtil;
import com.grss.jlsx.core.utils.SecurityUtil;
import com.grss.jlsx.core.utils.StringUtil;
import com.grss.jlsx.core.utils.XmlUtil;
import com.grss.jlsx.core.wx.constant.WXConstant;

public class PayRequest {
	/**
	 * 获取微信支付参数
	 * @return
	 */
	/*public Map<String, String> getPayParams(HttpServletRequest request,HttpServletResponse response){
		Map<String, String> result = new HashMap<String, String>();
		try {
			//接收财付通通知的URL
			String notify_url = "http://127.0.0.1:8180/tenpay_api_b2c/payNotifyUrl.jsp";
			String currTime = DateUtil.date2Str(new Date(), "yyyyMMddHHmmss");
			//8位日期
			String strTime = currTime.substring(8, currTime.length());
			String strRandom = RandomUtil.buildRandom(4) + "";
			//10位序列号,可以自行调整。
			String strReq = strTime + strRandom;
			//订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
			String out_trade_no = strReq;
			
			PackageRequestHandler packageReqHandler = new PackageRequestHandler(request, response);//生成package的请求类 
			PrepayIdRequestHandler prepayReqHandler = new PrepayIdRequestHandler(request, response);//获取prepayid的请求类
			ClientRequestHandler clientHandler = new ClientRequestHandler(request, response);
			packageReqHandler.setKey(WXConstant.PARTNER_KEY);

			int retcode;
			String retmsg = "";
			String xml_body = "";
			//获取token值 
			String token = AccessTokenRequestHandler.getAccessToken();
			if (!"".equals(token)) {
				//设置package订单参数
				packageReqHandler.setParameter("bank_type", "WX");//银行渠道
				packageReqHandler.setParameter("body", "测试"); //商品描述   
				packageReqHandler.setParameter("notify_url", notify_url); //接收财付通通知的URL  
				packageReqHandler.setParameter("partner", WXConstant.PARTNER); //商户号    
				packageReqHandler.setParameter("out_trade_no", out_trade_no); //商家订单号   
				packageReqHandler.setParameter("total_fee", "1"); //商品金额,以分为单位  
				packageReqHandler.setParameter("spbill_create_ip",request.getRemoteAddr()); //订单生成的机器IP，指用户浏览器端IP  
				packageReqHandler.setParameter("fee_type", "1"); //币种，1人民币   66
				packageReqHandler.setParameter("input_charset", "GBK"); //字符编码

				//获取package包
				String packageValue = packageReqHandler.getRequestURL();

				String noncestr = WXUtil.getNonceStr();
				String timestamp = WXUtil.getTimeStamp();
				String traceid = "";
				////设置获取prepayid支付参数
				prepayReqHandler.setParameter("appid", WXConstant.APP_ID);
				prepayReqHandler.setParameter("appkey", WXConstant.APP_KEY);
				prepayReqHandler.setParameter("noncestr", noncestr);
				prepayReqHandler.setParameter("package", packageValue);
				prepayReqHandler.setParameter("timestamp", timestamp);
				prepayReqHandler.setParameter("traceid", traceid);

				//生成获取预支付签名
				String sign = prepayReqHandler.createSHA1Sign();
				//增加非参与签名的额外参数
				prepayReqHandler.setParameter("app_signature", sign);
				prepayReqHandler.setParameter("sign_method",
						WXConstant.SIGN_METHOD);
				String gateUrl = WXConstant.GATEURL + token;
				prepayReqHandler.setGateUrl(gateUrl);

				//获取prepayId
				String prepayid = prepayReqHandler.sendPrepay();
				//吐回给客户端的参数
				if (null != prepayid && !"".equals(prepayid)) {
					//输出参数列表
					result.put("appid", WXConstant.APP_ID);
					result.put("appkey", WXConstant.APP_KEY);
					result.put("noncestr", noncestr);
					result.put("package", "Sign=WXPay");
					result.put("partnerid", WXConstant.PARTNER);
					result.put("prepayid", prepayid);
					result.put("timestamp", timestamp);
					
					//生成签名参数
					clientHandler.setParameter("appid", WXConstant.APP_ID);
					clientHandler.setParameter("appkey", WXConstant.APP_KEY);
					clientHandler.setParameter("noncestr", noncestr);
					clientHandler.setParameter("package", "Sign=WXPay");
					clientHandler.setParameter("partnerid", WXConstant.PARTNER);
					clientHandler.setParameter("prepayid", prepayid);
					clientHandler.setParameter("timestamp", timestamp);
					//生成签名
					sign = clientHandler.createSHA1Sign();
					result.put("sign", sign);
					retmsg = "OK";
					result.put("retmsg", retmsg);
				} else {
					retcode = -2;
					retmsg = "错误：获取prepayId失败";
					result.put("retmsg", retmsg);
				}
			} else {
				retcode = -1;
				retmsg = "错误：获取不到Token";
				result.put("retmsg", retmsg);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return result;
	}*/
	public Map<String, String> getPayParams(Map<String, String> params){
		params.put("notify_url", WXConstant.NOTIFY_URL);
		params.put("appid", WXConstant.APP_ID);
		params.put("mch_id", WXConstant.MCH_ID);
		params.put("nonce_str", StringUtil.getUUID());
		params.put("trade_type", WXConstant.TRADE_TYPE);
		params.put("sign", SecurityUtil.sign(params));
		String xml = RestClientUtil.post(WXConstant.PREPAY_URL, XmlUtil.mapToXml(params));
		return XmlUtil.parseXmlToMap(xml);
	}
	
}
