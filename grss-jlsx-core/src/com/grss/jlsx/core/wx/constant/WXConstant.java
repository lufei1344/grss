package com.grss.jlsx.core.wx.constant;

public class WXConstant {
	/**
	 * 商家可以考虑读取配置文件
	 */
	  public static final String APP_ID = "wx81081a8c63bf91af";//微信开发平台应用id
	  public static final String MCH_ID = "1323543101";
	 // public static final String NOTIFY_URL = "http://grss.goodbuild.cn/grss/api/updateOrderStatus";
	  public static final String NOTIFY_URL = "http://101.201.44.233/grss/api/updateOrderStatus";
	  public static final String REFUND_URL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
	  public static final String BODY = "教练随行订单";
	  public static final String TRADE_TYPE = "APP";
	  public static final String ATTACH = "教练随行订单支付";
	  public static final String PREPAY_URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	  public static final String PAY_KEY = "12345678901234567890123456789012";
	  public static final String PACKAGE = "Sign=WXPay";
}
