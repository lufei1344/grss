package com.grss.jlsx.admin.process.timer;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssAssets;
import com.grss.jlsx.core.bean.GrssCourse;
import com.grss.jlsx.core.bean.GrssOrder;
import com.grss.jlsx.core.bean.GrssPay;
import com.grss.jlsx.core.bean.GrssRefund;
import com.grss.jlsx.core.bean.GrssSysConfig;
import com.grss.jlsx.core.enums.HTTPMethod;
import com.grss.jlsx.core.utils.RestClientUtil;
import com.grss.jlsx.core.utils.SecurityUtil;
import com.grss.jlsx.core.utils.StringUtil;
import com.grss.jlsx.core.utils.XmlUtil;
import com.grss.jlsx.core.wx.constant.WXConstant;

public class OrderTime extends BaseTimer {

	@Override
	public void run() {
		GrssSysConfig sysConfig = grssSysConfigService.findByName("orderMissTime");
		if(sysConfig != null && !StringUtil.empty(sysConfig.getValue())){
			List<GrssOrder> list = grssOrderService.findOrderCurrentDate(Integer.parseInt(sysConfig.getValue()));
			if(list != null && list.size() > 0){
				for(GrssOrder order : list){
					GrssPay pay = grssPayService.findGrssPayByOrderId(order.getId());
					if(!StringUtil.empty(pay.getTransactionId()) && !pay.getTransactionId().equals("''")){
						if(this.refund(order.getId(), pay.getRealAmount().intValue() + "", pay.getPayChannel(),pay.getTransactionId())){
							order.setState(3);
							grssOrderService.updateByGrssOrder(order);
							GrssCourse course = grssCourseService.findById(order.getCourseId());
							GrssAssets assets = grssAssetsService.findByUserId(course.getCoachId());
							assets.setAmount((Float.parseFloat(assets.getAmount()) - pay.getRealAmount().intValue()) + "");
							grssAssetsService.updateByGrssAssets(assets);
						}
					}
					
				}
			}
		}
	}
	
	private boolean refund(String orderNo,String refundAmount,int channel,String transactionId){
		int isSuccess = 0;
		String reason = "教练长时间未处理订单，自动退款给用户";
		String refundNo = StringUtil.getUUID();
		if(channel == 0){
			Map<String, String> params = new HashMap<>();
			params.put("appid", WXConstant.APP_ID);
			params.put("mch_id", WXConstant.MCH_ID);
			params.put("nonce_str", StringUtil.getUUID());
			params.put("op_user_id", WXConstant.MCH_ID);
			params.put("out_refund_no", refundNo); //商户侧传给微信的订单号
			params.put("out_trade_no", orderNo); //退款订单号
			params.put("refund_fee", (Integer.parseInt(refundAmount) * 100) + "");
			params.put("total_fee", (Integer.parseInt(refundAmount) * 100) + "");
			params.put("transaction_id", transactionId);
			params.put("sign", SecurityUtil.sign(params));
			String xml = RestClientUtil.sendHTTPRequest(HTTPMethod.POST, WXConstant.REFUND_URL, XmlUtil.mapToXml(params), "/Users/xuelong/grss/apiclient_cert.p12", WXConstant.MCH_ID);
			Map<String, String> map = XmlUtil.parseXml2Map(xml);
			if(map != null && map.size() > 0){
				if(map.get("return_code").equals("SUCCESS") && map.get("return_msg").equals("OK") && map.get("result_code").equals("SUCCESS")){
					isSuccess = 1;
				}
			}
			GrssRefund grssRefund = new GrssRefund(refundNo, refundAmount, orderNo, reason, isSuccess, new Date());
			grssRefundService.saveRefund(grssRefund);
		}else{
				/*Map<String, Object> params = new HashMap<>();
				params.put("out_trade_no", orderNo);
				params.put("trade_no", transactionId);
				params.put("refund_amount", Float.parseFloat(refundAmount));
				params.put("refund_reason", reason);
				params.put("out_request_no", "HZ01RF001");
				AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do"
						,AliPayConstant.APP_ID,AliPayConstant.PRIVATE_KEY,"json","GBK",AliPayConstant.PUBLIC_KEY);
				AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
				request.setBizContent(JSON.toJSONString(params));
				AlipayTradeRefundResponse response = alipayClient.execute(request);
				System.out.println(response.getBody());
				System.out.println(response.getMsg());*/
			/*String batchNo = DateUtil.getDate() + DateUtil.getTimestamp() +  DateUtil.getThree();
			
			String detailData = transactionId + "^" + refundAmount + "^" + "协商退款";
			Map<String, String> sParaTemp = new HashMap<String, String>();
			sParaTemp.put("service", AliPayConstant.service);
	        sParaTemp.put("partner", AliPayConstant.partner);
	        sParaTemp.put("_input_charset", AliPayConstant.input_charset);
			sParaTemp.put("notify_url", AliPayConstant.notify_urlnull);
			sParaTemp.put("seller_user_id", AliPayConstant.seller_user_id);
			sParaTemp.put("refund_date", AliPayConstant.refund_date);
			sParaTemp.put("batch_no", batchNo);
			sParaTemp.put("batch_num", "1");
			sParaTemp.put("detail_data", detailData);
			String url = AlipayHandle.buildRequest(sParaTemp);
			System.out.println(url);
			String data = null;
			try {
				data = RestClientUtil.sendHTTPRequest(new URL(url),null,null,HTTPMethod.GET);
				System.out.println(data);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(data);*/
		}
		return (isSuccess != 0);
	}
}
