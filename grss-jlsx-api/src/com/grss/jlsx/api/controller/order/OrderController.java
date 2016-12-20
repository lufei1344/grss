package com.grss.jlsx.api.controller.order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grss.jlsx.api.controller.BaseController;
import com.grss.jlsx.api.process.annotation.AuthorityAnnotation;
import com.grss.jlsx.api.process.util.DateUtil;
import com.grss.jlsx.api.process.wx.pay.PayRequest;
import com.grss.jlsx.api.to.ResultDataTO;
import com.grss.jlsx.core.bean.GrssAssets;
import com.grss.jlsx.core.bean.GrssCourse;
import com.grss.jlsx.core.bean.GrssOrder;
import com.grss.jlsx.core.bean.GrssPay;
import com.grss.jlsx.core.bean.GrssPromo;
import com.grss.jlsx.core.service.GrssAssetsService;
import com.grss.jlsx.core.service.GrssCourseService;
import com.grss.jlsx.core.service.GrssOrderService;
import com.grss.jlsx.core.service.GrssPayService;
import com.grss.jlsx.core.service.GrssPromoService;
import com.grss.jlsx.core.utils.SecurityUtil;
import com.grss.jlsx.core.utils.StringUtil;
import com.grss.jlsx.core.utils.XmlUtil;
import com.grss.jlsx.core.wx.constant.WXConstant;

/***
 * @comment 订单控制器
 * com.grss.jlsx.api.controller.order
 * OrderController
 * 2016年3月27日 
 * 下午4:09:54
 * @author 
 */
@Controller
@RequestMapping(value="/api")
public class OrderController extends BaseController{
	@Resource
	private GrssCourseService grssCourseService;
	@Resource
	private GrssOrderService grssOrderService;
	@Resource
	private GrssPromoService GrssPromoService;
	@Resource
	private GrssPayService grssPayService;
	@Resource
	private GrssAssetsService grssAssetsService;
	/**
	 * @comment 获取课程订单列表  有分页
	 * @param token 教练登录token
	 * @param state 1 未处理 2已完成
	 * @return
	 * 2016年3月11日
	 * 上午9:59:29
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="getCourseOrder",method=RequestMethod.POST)
    public ResultDataTO getCourseOrder(@RequestParam(value="token",required=false)String token,@RequestParam(value="state",required=false)String state){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssCourse grssCourse=grssCourseService.findByCoachId(userId);
			if(grssCourse==null){
				grssCourse=new GrssCourse();
				grssCourse.setId(StringUtil.getUUID());
			}
			Map<String,Object> params=new HashMap<String, Object>();
			params.put("state", state);
			params.put("courseId", grssCourse.getId());
			List<GrssOrder> orders=grssOrderService.findOrdersByCourseId(params);
			return resultSuccess("获取课程订单成功！",orders);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("获取课程订单异常！");
		}
    }
	
	/**
	 * @comment 获取课程订单数量
	 * @param token 教练登录token
	 * @return
	 * 2016年3月11日
	 * 上午9:59:29
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="getOrderCounts",method=RequestMethod.POST)
    public ResultDataTO getOrderCounts(@RequestParam(value="token",required=false)String token,@RequestParam(value="dayNum",required=false)String dayNum){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssCourse grssCourse=grssCourseService.findByCoachId(userId);
			if(grssCourse==null){
				grssCourse=new GrssCourse();
				grssCourse.setId(StringUtil.getUUID());
			}
			Map<String,Object> params=new HashMap<String, Object>();
			Integer dnum=Integer.parseInt(StringUtil.empty(dayNum)?"10":dayNum);
			Date date=DateUtil.getAddOrSubtractDate(-dnum);
			String startDate=DateUtil.date2Str(date, "yyyy-MM-dd");
			params.put("courseId", grssCourse.getId());
			params.put("startDate", startDate);
			List<Map<String,Object>> orderCounts=grssOrderService.findOrderCounts(params);
			return resultSuccess("获取课程数量成功！",orderCounts);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("获取课程数量异常！");
		}
    }
	
	/**
	 * @comment 处理订单
	 * @param token 教练token
	 * @param orderId 订单ID
	 * @param vcIds 栏目名称1,一级分类名称1,二级名称1,三级名称1,视频ID1；栏目名称2,一级分类名称2,二级名称2,三级名称2,视频ID2
	 * @return
	 * 2016年3月27日
	 * 下午4:30:29
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="handleOrder",method=RequestMethod.POST)
    public ResultDataTO handleOrder(@RequestParam(value="token",required=false)String token,
    		@RequestParam(value="orderId",required=false)String orderId,
    		@RequestParam(value="vcIds",required=false)String vcIds,@RequestParam(value="coachComment",required=false)String coachComment){
		try {
			String coachId=SecurityUtil.token2UserId(token);
			GrssCourse grssCourse=grssCourseService.findByCoachId(coachId);
			if(grssCourse==null){
				grssCourse=new GrssCourse();
				grssCourse.setId(StringUtil.getUUID());
			}
			GrssOrder grssOrder=grssOrderService.findById(orderId);
			if(!grssCourse.getId().equals(grssOrder.getCourseId())){
				return resultFaild("处理订单异常！");
			}
			grssOrder.setCoachComment(coachComment);
			grssOrder.setState(2);
			grssOrderService.updateByGrssOrder(grssOrder);
			grssOrderService.addOrderResult(orderId,vcIds);
			return resultSuccess("订单处理成功！",null);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("处理订单异常！");
		}
    }
	
	
	/**
	 * @comment 处理订单
	 * @param token 教练token
	 * @param orderId 订单ID
	 * @param vcIds 栏目名称1,一级分类ID1,二级ID1,三级ID1,视频ID1；栏目名称2,一级分类ID2,二级ID2,三级ID2,视频ID2
	 * @return
	 * 2016年3月27日
	 * 下午4:30:29
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="handleOrder_V2",method=RequestMethod.POST)
    public ResultDataTO handleOrder_V2(@RequestParam(value="token",required=false)String token,
    		@RequestParam(value="orderId",required=false)String orderId,
    		@RequestParam(value="vcIds",required=false)String vcIds,@RequestParam(value="coachComment",required=false)String coachComment){
		try {
			String coachId=SecurityUtil.token2UserId(token);
			GrssCourse grssCourse=grssCourseService.findByCoachId(coachId);
			if(grssCourse==null){
				grssCourse=new GrssCourse();
				grssCourse.setId(StringUtil.getUUID());
			}
			GrssOrder grssOrder=grssOrderService.findById(orderId);
			if(!grssCourse.getId().equals(grssOrder.getCourseId())){
				return resultFaild("处理订单异常！");
			}
			grssOrder.setCoachComment(coachComment);
			grssOrder.setState(2);
			grssOrderService.updateByGrssOrder(grssOrder);
			grssOrderService.addOrderResult_V2(orderId,vcIds);
			return resultSuccess("订单处理成功！",null);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("处理订单异常！");
		}
    }
	/**
	 * 创建订单
	 * @param request 
	 * @param token 用户登录证明
	 * @param amount 订单金额
	 * @param courseId 课程ID
	 * @param userComment 用户备注说明
	 * @param channel 支付渠道(0微信 1支付宝)
	 * @param attach 自定义数据 默认（教练随行订单支付）
	 * @param body 订单描述（商品名称 默认 （教练随行订单 + 课程号）
	 * @param promoId 优惠劵（码）Id
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="createOrder",method=RequestMethod.POST)
	public ResultDataTO createOrder(HttpServletRequest request,@RequestParam(value = "token",required = false) String token,
			@RequestParam(value = "amount",required = false) String amount,
			@RequestParam(value = "courseId",required = false) String courseId,
			@RequestParam(value = "userComment",required = false) String userComment,
			@RequestParam(value = "channel",required = false) String channel,
			@RequestParam(value = "promoId",required = false) String promoId,
			@RequestParam(value = "attach",required = false) String attach,
			@RequestParam(value = "body",required = false) String body){
		try{
			int amountInt = 0;
			String[] type = {"优惠码","优惠卷"};
			GrssCourse course = grssCourseService.findById(courseId);
			GrssPromo Promo = GrssPromoService.findPromoById(promoId);
			if(Promo != null){
				if( Promo.getEndDate().getTime() > new Date().getTime()){
					return resultFaild(type[Promo.getType()]+"已过期！");
				}
				if(Promo.getState() > 1){
					return resultFaild("该优惠劵已使用！");
				}
				amountInt = Integer.parseInt(StringUtil.trim(amount)) + Promo.getPrice();
			}
			amountInt = Integer.parseInt(StringUtil.trim(amount));
			if(course.getPrice().floatValue() > amountInt){
				 return resultFaild("支付失败,您的支付金额不足！");
			}
			String userId = SecurityUtil.token2UserId(token);
			GrssOrder grssOrder = new GrssOrder();
			String orderId = StringUtil.getUUID();
			grssOrder.setId(orderId);
			grssOrder.setAmount("0".equals(StringUtil.trim(channel)) ? (Integer.parseInt(amount) / 100) + "" : amount);
			grssOrder.setUserId(userId);
			grssOrder.setCourseId(courseId);
			grssOrder.setUserComment(userComment);
			grssOrder.setOrderDate(new Date());
			grssOrder.setState(0);
			grssOrderService.addByGrssOrder(grssOrder);
			//微信支付
			if("0".equals(StringUtil.trim(channel))){
				PayRequest payRequest = new PayRequest();
				Map<String, String> params = new HashMap<>();
				if(!StringUtil.empty(promoId)){
					params.put("goods_tag", promoId);
				}
				params.put("attach", StringUtil.empty(attach) ? WXConstant.ATTACH : attach);
				params.put("body", StringUtil.empty(body) ? WXConstant.BODY + courseId : body) ;
				params.put("spbill_create_ip", request.getRemoteAddr().equals("0:0:0:0:0:0:0:1") ? "127.0.0.1" : request.getRemoteAddr());
				params.put("total_fee",amount);
				params.put("out_trade_no", orderId);
				Map<String,String> resultData = payRequest.getPayParams(params);
				if(resultData != null && resultData.size() > 0){
					resultData.put("appid", WXConstant.APP_ID);
					resultData.put("partnerid",WXConstant.MCH_ID);
					resultData.put("package",WXConstant.PACKAGE);
					resultData.put("noncestr",StringUtil.getUUID());
					resultData.put("timestamp",DateUtil.getTimestamp()+"");
					resultData.put("sign",SecurityUtil.sign(resultData));
					return resultSuccess("正常返回", resultData);
				}
				return resultFaild("微信支付失败！");
			}else{
				Map<String, Object> resultDataMap = new HashMap<>();
				resultDataMap.put("orderNo", orderId);
				return resultSuccess("正常返回", resultDataMap);
			}
				
		}catch(Exception e){
			e.printStackTrace();
			return resultFaild("订单生成失败");
		}
	}
	/**
	 * 用户订单
	 * @param token
	 * @return
	 */
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "getStudentCourseOrder",method=RequestMethod.POST)
	public @ResponseBody ResultDataTO getStudentCourseOrder(@RequestParam(value = "token",required = false) String token){
		String userId = SecurityUtil.token2UserId(token);
		List<GrssOrder> orderList = grssOrderService.findOrderByUserId(userId);
		return resultSuccess("正常返回", orderList);
	}
	/**
	 * 微信支付成功后回调的接口
	 * @param orderId
	 * @param payId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateOrderStatus")
	public Object updateOrderStatus(HttpServletRequest request,HttpServletResponse response){
		try{
			Map<String, String> params = new HashMap<>();
			params.put("return_code", "<![CDATA[SUCCESS]]>");
			params.put("return_msg", "<![OK]]>");
			String resultXml = XmlUtil.mapToXml(params);
			Map<String,String> resutl = XmlUtil.parseXmlToMap(request.getInputStream());
			if(resutl != null && !resutl.isEmpty() && "SUCCESS".equals(resutl.get("result_code"))){
				float totalFee = Integer.parseInt(resutl.get("total_fee")) / 100;
				String orderNo = resutl.get("out_trade_no");
				GrssPay grssPay = grssPayService.findGrssPayByOrderId(orderNo);
				if(grssPay != null && grssPay.getPayFinishTime() != null){
					return resultXml;
				}
				
				GrssPay pay = new GrssPay();
				pay.setOrderId(orderNo);
				pay.setPayAccount(resutl.get("openid"));
				pay.setPayChannel(0);
				pay.setPayFinishTime(new Date());
				pay.setPayTime(new Date());
				pay.setPayId(StringUtil.getUUID());
				pay.setRealAmount(BigDecimal.valueOf(totalFee));
				pay.setShouldAmount(BigDecimal.valueOf(totalFee));
				this.updateOrder(orderNo,totalFee+"",resutl.get("transaction_id"),pay);
				return resultXml;
				
			}else{
				return null;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	/**
	 * 支付宝支付成功后回调接口
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/updateOrderStatusAli")
	public String updateOrderStatusAli(HttpServletRequest request,HttpServletResponse response){
		String status = "SUCCESS";
		try {
			log.info("===========================================start");
			String orderNo = request.getParameter("out_trade_no");
			GrssPay grssPay = grssPayService.findGrssPayByOrderId(orderNo);
			if(grssPay != null && grssPay.getPayFinishTime() != null){
				return status;
			}
			float totalFee = Float.parseFloat(StringUtil.trim(request.getParameter("total_fee")));
			float price = Float.parseFloat(StringUtil.trim(request.getParameter("price")));
			String tradeNo = StringUtil.trim(request.getParameter("trade_no")); 
			
			GrssPay pay = new GrssPay();
			pay.setOrderId(orderNo);
			pay.setPayAccount(request.getParameter("seller_email"));
			pay.setPayChannel(1);
			pay.setPayFinishTime(new Date());
			pay.setPayTime(new Date());
			pay.setPayId(StringUtil.getUUID());
			pay.setRealAmount(BigDecimal.valueOf(totalFee));
			pay.setShouldAmount(BigDecimal.valueOf(price));
			this.updateOrder(orderNo,price+"",tradeNo,pay);
			log.info("===========================================end");
			return status;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 更新对应的信息
	 * @param orderNo
	 * @param amount
	 */
	private void updateOrder(String orderNo,String amount,String transactionId,GrssPay pay){
		log.info("orderNo : " + orderNo +" ==== " + " amount" + amount + " ===== transactionId" + transactionId);
		GrssOrder order = grssOrderService.findById(orderNo);
		GrssCourse course = grssCourseService.findById(order.getCourseId());
		GrssAssets assets = grssAssetsService.findByUserId(course.getCoachId());
		order.setPayDate(new Date());
		order.setState(1);
		assets.setAmount(Float.parseFloat(StringUtil.empty(assets.getAmount()) ? "0" :  assets.getAmount()) + Float.parseFloat(StringUtil.empty(amount) ? "0" : amount) + "");
		grssAssetsService.updateByGrssAssets(assets);
		grssOrderService.updateByGrssOrder(order);
		pay.setTransactionId(transactionId);
		grssPayService.saveGrssPay(pay);
	}
}
