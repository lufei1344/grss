package com.grss.jlsx.api.process.util;

import org.apache.log4j.Logger;

import cn.jiguang.commom.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushUtils {
	private static final Logger LOG = Logger.getLogger(JPushUtils.class);
	//用户端
	private static final String appKey = "d225909eb69bf26a2256f519";
	private static final String masterSecret = "fd201133b9d41ecf49b980a4";
	//教练端
	private static final String appKey_jl = "a4e61e18b94c5bd820e4300b";
	private static final String masterSecret_jl = "921cb03796ea6b637a6a0f2a";
	private static JPushClient jpushClient;
	private static JPushClient jpushClient_jl;
	 public static PushPayload buildPushObject_andriod( String content,String userid) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android())
	                .setAudience(Audience.alias(userid))
	               /* .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.tag("tag"))
	                        .addAudienceTarget(AudienceTarget.alias(userid))
	                        .build())*/
	                .setNotification(Notification.alert(content)).build();
	                /*.setMessage(Message.newBuilder()
	                        .setMsgContent(content)
	                        .addExtra("from", "JPush")
	                        .build())
	                .build();*/
	    }
	 public static PushPayload buildPushObject_ios( String content,String userid) {
		 return PushPayload.newBuilder()
				 .setPlatform(Platform.ios())
				 .setAudience(Audience.alias(userid))
				 /*.setAudience(Audience.newBuilder()
						 .addAudienceTarget(AudienceTarget.tag("tag"))
						 .addAudienceTarget(AudienceTarget.alias(userid))
						 .build())*/
				 .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert("您有一条新的订单")
	                                .build())
	                        .build())
	                 .setMessage(Message.content(content)).build();
		 /*.setMessage(Message.newBuilder()
	                        .setMsgContent(content)
	                        .addExtra("from", "JPush")
	                        .build())
	                .build();*/
	 }
	 
	 public static void sendPush(String content,String userid) {
		    // HttpProxy proxy = new HttpProxy("localhost", 3128);
		    // Can use this https proxy: https://github.com/Exa-Networks/exaproxy
		 	if(jpushClient == null){
		 		ClientConfig clientConfig = ClientConfig.getInstance();
		        jpushClient = new JPushClient(masterSecret, appKey, null, clientConfig);
		 	}
			
	        
	        // For push, all you need do is to build PushPayload object.
	        try {
	        	//PushResult result = jpushClient.sendAndroidNotificationWithAlias("title", content, null, userid);
	           PushResult result = jpushClient.sendPush(buildPushObject_andriod(content,userid));
	            LOG.info("Got result - " + result);
	             result = jpushClient.sendPush(buildPushObject_ios(content,userid));
	             LOG.info("Got result - " + result);
	        } catch (APIConnectionException e) {
	            LOG.error("Connection error. Should retry later. ", e);
	            
	        } catch (APIRequestException e) {
	            LOG.error("Error response from JPush server. Should review and fix it. ", e);
	            LOG.info("HTTP Status: " + e.getStatus());
	            LOG.info("Error Code: " + e.getErrorCode());
	            LOG.info("Error Message: " + e.getErrorMessage());
	            LOG.info("Msg ID: " + e.getMsgId());
	        }
		}
	 public static void sendPushJL(String content,String userid) {
		 // HttpProxy proxy = new HttpProxy("localhost", 3128);
		 // Can use this https proxy: https://github.com/Exa-Networks/exaproxy
		 if(jpushClient_jl == null){
			 ClientConfig clientConfig = ClientConfig.getInstance();
			 jpushClient_jl = new JPushClient(masterSecret_jl, appKey_jl, null, clientConfig);
		 }
		 
		 
		 // For push, all you need do is to build PushPayload object.
		 try {
			 PushResult result = jpushClient_jl.sendPush(buildPushObject_andriod(content,userid));
			 LOG.info("Got result - " + result);
			 result = jpushClient_jl.sendPush(buildPushObject_ios(content,userid));
			 LOG.info("Got result - " + result);
			 
		 } catch (APIConnectionException e) {
			 LOG.error("Connection error. Should retry later. ", e);
			 
		 } catch (APIRequestException e) {
			 LOG.error("Error response from JPush server. Should review and fix it. ", e);
			 LOG.info("HTTP Status: " + e.getStatus());
			 LOG.info("Error Code: " + e.getErrorCode());
			 LOG.info("Error Message: " + e.getErrorMessage());
			 LOG.info("Msg ID: " + e.getMsgId());
		 }
	 }

	 public static void main(String[] args){
		 for(int i=0; i<4; i++){
			 JPushUtils.sendPush("测试"+i,"b0a6f737bdc6424ab6e7f913c07b6889"); 
		 }
		 
	 }
}
