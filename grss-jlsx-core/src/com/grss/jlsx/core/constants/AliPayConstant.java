package com.grss.jlsx.core.constants;

import com.grss.jlsx.core.utils.DateUtil;

public class AliPayConstant {
	//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

		// 合作身份者ID，签约账号，以2088开头由16位纯数字组成的字符串，查看地址：https://b.alipay.com/order/pidAndKey.htm
		public static String partner = "2088121913145957";
		
		// 收款支付宝账号，以2088开头由16位纯数字组成的字符串，一般情况下收款账号就是签约账号
		public static String seller_user_id = partner;

		// 商户的私钥,需要PKCS8格式，RSA公私钥生成：https://doc.open.alipay.com/doc2/detail.htm?spm=a219a.7629140.0.0.nBDxfy&treeId=58&articleId=103242&docType=1
		public static String private_key = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBANS4Vp5ncIqVGw0BkacjtQR/kSfTB1jOBdt2nNkmpea0JmgDlOqT2NTKSVGrhrVLClPiNPTCbHCs196x1xjkuIEvztMeoXWrkU7bHlTUoYLqq/cU9DNvSjCb8yjSTmQlAahhM98lSOSPgo3ak9tsJhz1eYqdtotXdRPKtG6cO5uPAgMBAAECgYB/TotYZeOurKnx0LyQ4QfW11nSEbPV7AcJXyVjuIOVXL+XhH09HpqoTyAuJo+KNIzLwxeaXDl1/Zt8BccLeOcKIepsBD9vj8HJt3x0yIwpYOiZ0DyI8MbQAQtupQDatWGfr9+Hf3xBKgfwPvLmR03THj6vqMCQsjDAv4pqSgkrUQJBAPhraIrgwzEo6PguuhQilA7TGj+LMScUxiE+CqczUmXBCZ+Me/Lc2pDBSPB2hLtUvfuL9YLU9c0DMIO7yqPxN4sCQQDbNg3xPDo7HRtGbn/rbIcMFNcwWru68OQTy1MWBucmkmVV7Nac53+ij6ZHGmS8QJ0SvqrX6ckcoEMd9hvyAIyNAkEAstJehtoUqCaSzVSVjjj161X65xMDZuaFWRiYApPnFGhIzRkLgF+K1fjM0IwAL/loaNLvACbcaZ+KJMnhrPHO0QJAB2Kq1ZXR4Gv6n0TZynS9mAqbtWVZLdMv2/rdscBJyWLlRx/TmzWxdyif0YVyH2WN5TPHTb7yp6Q+nqPMDTs3gQJBAJ9t3+wyDBtPb24S4Fz8l14IMtTSmoOmMoCgkIizO60O1nllHot3yDHZmwi3y1TASiwaOMySKgy8TD4ysgfxc7I=";
		
		// 支付宝公钥,查看地址：https://b.alipay.com/order/pidAndKey.htm 
		//public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCnxj/9qwVfgoUh/y2W89L6BkRAFljhNhgPdyPuBV64bfQNN1PjbCzkIM6qRdKBoLPXmKKMiFYnkd6rAoprih3/PrQEB/VsW8OoM8fxn67UDYuyBTqA23MML9q1+ilIZwBC2AQ2UBVOrFXfFl75p6/B5KsiNG9zpgmLCUYuLkxpLQIDAQAB";
		public static String alipay_public_key  = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDDI6d306Q8fIfCOaTXyiUeJHkrIvYISRcc73s3vF1ZT7XN8RNPwJxo8pWaJMmvyTn9N4HQ632qJBVHf8sxHi/fEsraprwCtzvzQETrNRwVxLO5jVmRGi60j8Ue1efIlzPXV9je9mkjzOmdssymZkh2QhUrCmZYI/FCEa3/cNMW0QIDAQAB";

		// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String notify_url = "http://商户网址/create_direct_pay_by_user-JAVA-UTF-8/notify_url.jsp";

		// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		public static String return_url = "http://商户网址/create_direct_pay_by_user-JAVA-UTF-8/return_url.jsp";

		// 签名方式
		public static String sign_type = "RSA";
		
		// 调试用，创建TXT日志文件夹路径，见AlipayCore.java类中的logResult(String sWord)打印方法。
		public static String log_path = "C:\\";
			
		// 字符编码格式 目前支持 gbk 或 utf-8
		public static String input_charset = "utf-8";
			
		 //退款日期 时间格式 yyyy-MM-dd HH:mm:ss
	    public static String refund_date = DateUtil.getDateFormatter();
			
		// 调用的接口名，无需修改
		public static String service = "refund_fastpay_by_platform_pwd";
		
		/**
	     * 支付宝提供给商户的服务接入网关URL(新)
	     */
	    public static final String ALIPAY_GATEWAY_NEW = "https://mapi.alipay.com/gateway.do?";
}
