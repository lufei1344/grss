package com.grss.jlsx.api.controller.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.grss.jlsx.api.controller.BaseController;
import com.grss.jlsx.api.process.annotation.AuthorityAnnotation;
import com.grss.jlsx.api.process.enums.GrssUserType;
import com.grss.jlsx.api.process.upload.UploadFile;
import com.grss.jlsx.api.process.util.ParsePropertiesUtil;
import com.grss.jlsx.api.to.ResultDataTO;
import com.grss.jlsx.core.bean.GrssAssets;
import com.grss.jlsx.core.bean.GrssCourse;
import com.grss.jlsx.core.bean.GrssDrawMoney;
import com.grss.jlsx.core.bean.GrssFeedback;
import com.grss.jlsx.core.bean.GrssFollow;
import com.grss.jlsx.core.bean.GrssPromo;
import com.grss.jlsx.core.bean.GrssSysMessage;
import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.bean.GrssUserAccounts;
import com.grss.jlsx.core.bean.GrssUserEx;
import com.grss.jlsx.core.bean.GrssUserSetup;
import com.grss.jlsx.core.service.GrssAssetsService;
import com.grss.jlsx.core.service.GrssCoachAttestationService;
import com.grss.jlsx.core.service.GrssCourseService;
import com.grss.jlsx.core.service.GrssDrawMoneyService;
import com.grss.jlsx.core.service.GrssFeedbackService;
import com.grss.jlsx.core.service.GrssFollowService;
import com.grss.jlsx.core.service.GrssOrderService;
import com.grss.jlsx.core.service.GrssPromoService;
import com.grss.jlsx.core.service.GrssSysMessageService;
import com.grss.jlsx.core.service.GrssUserAccountsService;
import com.grss.jlsx.core.service.GrssUserExService;
import com.grss.jlsx.core.service.GrssUserService;
import com.grss.jlsx.core.service.GrssUserSetupService;
import com.grss.jlsx.core.utils.DateUtil;
import com.grss.jlsx.core.utils.KmUtil;
import com.grss.jlsx.core.utils.SecurityUtil;
import com.grss.jlsx.core.utils.StringUtil;
@Controller
@RequestMapping("/api")
public class UserController extends BaseController{
	@Resource
	private GrssUserService grssUserService;
	@Resource
	private GrssUserAccountsService grssUserAccountsService;
	@Resource
	private GrssFollowService grssFollowService;
	@Resource
	private GrssCourseService grssCourseService;
	@Resource
	private GrssCoachAttestationService grssCoachAttestationService;
	@Resource
	private GrssUserSetupService grssUserSetupService;
	@Resource
	private GrssFeedbackService grssFeedbackService;
	@Resource
	private GrssSysMessageService grssSysMessageService;
	@Resource
	private GrssPromoService grssPromoService;
	@Resource
	private GrssAssetsService grssAssetsService;
	@Resource
	private GrssDrawMoneyService grssDrawMoneyService;
	@Resource
	private GrssUserExService grssUserExService;
	@Resource
	private GrssOrderService grssOrderService;
	/**
	 * @comment 修改个人信息接口
	 * @param request
	 * @param token
	 * @param userPhoto
	 * @return
	 * 2016年1月28日
	 * 下午5:11:07
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	public ResultDataTO updateUser(HttpServletRequest request,@RequestParam(value = "token",required=false) String token,
			@RequestPart(value = "userPhoto",required = false) MultipartFile userPhoto){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssUser grssUser=grssUserService.findGrssUserByUserId(userId);
			if(userPhoto!=null){
				String imgPath=UploadFile.doUploadImg(userPhoto,null, DateUtil.getYear()+"-"+DateUtil.getMonth() + "-" + DateUtil.getDay());
				grssUser.setUserPhoto(imgPath);
			}
			String nikeName=request.getParameter("nikeName");
			String userSex=request.getParameter("userSex");
			String userDesc=request.getParameter("userDesc");
			String userHeight=request.getParameter("userHeight");
			String userWeight=request.getParameter("userWeight");
			String birthday=request.getParameter("birthday");
			String city=request.getParameter("city");
			String coachType=request.getParameter("coachType");
			String toClubName=request.getParameter("toClubName");
			if(!StringUtil.empty(nikeName)){
				grssUser.setNikeName(nikeName);
			}
			if(!StringUtil.empty(userSex)){
				grssUser.setUserSex(Integer.parseInt(userSex));
			}
			if(!StringUtil.empty(userDesc)){
				grssUser.setUserDesc(userDesc);
			}
			if(!StringUtil.empty(userHeight)){
				grssUser.setUserHeight(userHeight);
			}
			if(!StringUtil.empty(userWeight)){
				grssUser.setUserWeight(userWeight);
			}
			if(!StringUtil.empty(birthday)){
				Date bdate=DateUtil.stringToDate(birthday,DateUtil.PATTERN_DATE);
				grssUser.setBirthday(bdate);
				String constellation=DateUtil.getConstellation(bdate);
				grssUser.setConstellation(constellation);
			}
			if(!StringUtil.empty(city)){
				grssUser.setCity(city);
			}
			if(!StringUtil.empty(coachType)){
				grssUser.setCoachType(coachType);
			}
			if(!StringUtil.empty(toClubName)){
				grssUser.setToClubName(toClubName);
			}
			grssUserService.updateGrssUser(grssUser);
			grssUser.setToken(token);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.user.personalData.success"), grssUser);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild(ParsePropertiesUtil.getMessage("message.user.personalData.faild"));
		}
		
	}
	
	/**
	 * @comment 获取用户信息接口
	 * @param request
	 * @return
	 * 2016年1月30日
	 * 下午1:54:28
	 */
	@ResponseBody
	@RequestMapping(value="/getUser",method=RequestMethod.POST)
	public ResultDataTO getUser(@RequestParam(value = "token",required=false) String token,@RequestParam(value = "userId",required=false) String userId){
		GrssUser grssUser=grssUserService.findGrssUserByUserId(userId);
		Integer fansCount=grssFollowService.findFansCount(userId);
		Integer followCount=grssFollowService.findFollowCount(userId);
		grssUser.setFansCount(fansCount);
		grssUser.setFollowCount(followCount);
		String userType=grssUser.getUserType();
		if(GrssUserType.coach.name().equals(userType)){//教练指导人数
			GrssCourse grssCourse= grssCourseService.findByCoachId(userId);
			Integer orderCount=grssOrderService.findCoachOrderCount(grssCourse.getId());
			Integer userLevel=grssUserService.findCoachLevel(orderCount);
			Integer guidanceCount=grssCourseService.findGuidanceCount(userId);
			grssUser.setGuidanceCount(guidanceCount);
			grssUser.setUserLevel(userLevel);
		}
		if(!StringUtil.empty(token)){
			String loginUserId=SecurityUtil.token2UserId(token);
			Integer followRelationship=grssFollowService.findFollowRelationship(loginUserId, userId);
			grssUser.setFollowRelationship(followRelationship);
		}else{
			grssUser.setFollowRelationship(0);
		}
		return resultSuccess(ParsePropertiesUtil.getMessage("message.user.getUser.success"), grssUser);
	}
	
	
	/**
	 * 查找用户接口
	 * @param phone
	 * @param nikeName
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/searchUser",method=RequestMethod.POST)
	public ResultDataTO searchUser(@RequestParam(value = "token",required =false) String token,
			@RequestParam(value = "keyword",required =false) String keyword){
		try {
			String phoneReg="1[0-9]{10}";
			Pattern p = Pattern.compile(phoneReg);
			Matcher m = p.matcher(keyword);
			Map<String,Object> paramsMap=new HashMap<String, Object>();
			if(m.matches())
				paramsMap.put("phone", keyword);
			paramsMap.put("keyword",keyword);
			List<GrssUser> users=grssUserService.findGrssUserByKeyword(paramsMap);
			if(!StringUtil.empty(token)){
				String loginUserId=SecurityUtil.token2UserId(token);
				List<GrssUser> resultList=new ArrayList<GrssUser>();
				for (int i = 0; i < users.size(); i++) {
					GrssUser user=users.get(i);
					String userId=user.getUserId();
					Integer followRelationship=grssFollowService.findFollowRelationship(loginUserId, userId);
					user.setFollowRelationship(followRelationship);
					resultList.add(user);
				}
				return resultSuccess("用户查询成功！",resultList);
			}
			return resultSuccess("用户查询成功！",users);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("用户查询成功！");
		}
	}
	/**
	 * @comment 关注用户接口
	 * @param token 
	 * @param userId
	 * @return
	 * 2016年2月18日
	 * 下午12:28:07
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/followUser",method=RequestMethod.POST)
	public ResultDataTO followUser(@RequestParam(value = "token",required =false) String token,
			@RequestParam(value = "userId",required =false) String userId){
		try {
			String loginUserId=SecurityUtil.token2UserId(token);
			GrssFollow grssFollow=new GrssFollow();
			grssFollow.setFansId(loginUserId);
			grssFollow.setBeNoticedId(userId);
			grssFollowService.deleteByKey(grssFollow);
			grssFollowService.addByGrssFollow(grssFollow);
			Integer followRelationship=grssFollowService.findFollowRelationship(loginUserId, userId);
			grssFollow.setFollowRelationship(followRelationship);
			return resultSuccess("关注用户成功！",grssFollow);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("关注用户异常！");
		}
	}
	
	/**
	 * @comment 取消用户关注接口
	 * @param token 
	 * @param userId
	 * @return
	 * 2016年2月18日
	 * 下午13点
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/unFollowUser",method=RequestMethod.POST)
	public ResultDataTO unFollowUser(@RequestParam(value = "token",required =false) String token,
			@RequestParam(value = "userId",required =false) String userId){
		try {
			String loginUserId=SecurityUtil.token2UserId(token);
			GrssFollow grssFollow=new GrssFollow();
			grssFollow.setFansId(loginUserId);
			grssFollow.setBeNoticedId(userId);
			grssFollowService.deleteByKey(grssFollow);
			Integer followRelationship=grssFollowService.findFollowRelationship(loginUserId, userId);
			grssFollow.setFollowRelationship(followRelationship);
			return resultSuccess("取消关注成功！",grssFollow);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("取消关注异常！");
		}
	}
	
	/**
	 * @comment 关注的用户列表
	 * @param token
	 * @return
	 * 2016年2月18日
	 * 下午1:18:19
	 */
	@ResponseBody
	@RequestMapping(value="/followUserList",method=RequestMethod.POST)
	public ResultDataTO followUserList(@RequestParam(value = "token",required =false) String token,
			@RequestParam(value = "userId",required =false) String userId){
		try {
			String loginUserId=SecurityUtil.token2UserId(token);
			List<GrssUser> users=grssUserService.findFollowUsersByUserId(userId);
			List<GrssUser> resultList=new ArrayList<GrssUser>();
			for (int i = 0; i < users.size(); i++) {
				GrssUser user=users.get(i);
				String uId=user.getUserId();
				Integer followRelationship=grssFollowService.findFollowRelationship(loginUserId, uId);
				Integer fansCount=grssFollowService.findFansCount(userId);
				Integer followCount=grssFollowService.findFollowCount(userId);
				user.setFansCount(fansCount);
				user.setFollowCount(followCount);
				user.setFollowRelationship(followRelationship);
				resultList.add(user);
			}
			return resultSuccess("获取关注列表成功！", resultList);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("获取关注列表异常！");
		}
	}
	
	/**
	 * @comment 粉丝列表
	 * @param token
	 * @return
	 * 2016年2月18日
	 * 下午1:45:07
	 */
	@ResponseBody
	@RequestMapping(value="/fansUserList",method=RequestMethod.POST)
	public ResultDataTO fansUserList(@RequestParam(value = "token",required =false) String token,
			@RequestParam(value = "userId",required =false) String userId){
		try {
			String loginUserId=SecurityUtil.token2UserId(token);
			List<GrssUser> users=grssUserService.findFansUsersByUserId(userId);
			List<GrssUser> resultList=new ArrayList<GrssUser>();
			for (int i = 0; i < users.size(); i++) {
				GrssUser user=users.get(i);
				String uId=user.getUserId();
				Integer followRelationship=grssFollowService.findFollowRelationship(loginUserId, uId);
				user.setFollowRelationship(followRelationship);
				Integer fansCount=grssFollowService.findFansCount(userId);
				Integer followCount=grssFollowService.findFollowCount(userId);
				user.setFansCount(fansCount);
				user.setFollowCount(followCount);
				resultList.add(user);
			}
			return resultSuccess("获取粉丝列表成功！", resultList);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("获取粉丝列表异常！");
		}
	}
	/**
	 * @comment 用户系统设置(更改/获取)
	 * @param token
	 * @return
	 * 2016年2月18日
	 * 下午2:13:38
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/userSysSetup",method=RequestMethod.POST)
	public ResultDataTO userSysSetup(@RequestParam(value = "token",required =false) String token,
			@RequestParam(value = "reply",required=false) String reply,
			@RequestParam(value = "praise",required=false) String praise,
			@RequestParam(value = "privateChat",required=false) String privateChat,
			@RequestParam(value = "newFans",required=false) String newFans,
			@RequestParam(value = "systemMessage",required=false) String systemMessage){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssUserSetup grssUserSetup=grssUserSetupService.findGrssUserSetupByUseId(userId);
			if(!StringUtil.empty(reply)){
				grssUserSetup.setReply(Integer.parseInt(reply));
			}
			if(!StringUtil.empty(praise)){
				grssUserSetup.setPraise(Integer.parseInt(praise));
			}
			if(!StringUtil.empty(privateChat)){
				grssUserSetup.setPrivateChat(Integer.parseInt(privateChat));
			}
			if(!StringUtil.empty(newFans)){
				grssUserSetup.setNewFans(Integer.parseInt(newFans));
			}
			if(!StringUtil.empty(systemMessage)){
				grssUserSetup.setSystemMessage(Integer.parseInt(systemMessage));
			}
			if(!StringUtil.empty(systemMessage)||!StringUtil.empty(reply)||
					!StringUtil.empty(praise)||!StringUtil.empty(privateChat)||!StringUtil.empty(newFans)){
				grssUserSetupService.updateByGrssUserSetup(grssUserSetup);
			}
			return resultSuccess("获取/设置用户设置成功！",grssUserSetup);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("获取/设置用户设置异常！");
		}
	}
	
	/**
	 * @comment 用户反馈
	 * @param token
	 * @param comment
	 * @return
	 * 2016年2月18日
	 * 下午2:56:52
	 */
	@ResponseBody
	@RequestMapping(value="/feedback",method=RequestMethod.POST)
	public ResultDataTO feedback(@RequestParam(value = "token",required =false) String token,
			@RequestParam(value = "comment",required =false) String comment,@RequestPart(value = "commentImg",required = false) MultipartFile commentImg){
		try {
			String userId="游客反馈";
			if(!StringUtil.empty(token)){
				userId=SecurityUtil.token2UserId(token);
			}
			GrssFeedback feedback=new GrssFeedback();
			if(commentImg!=null){
				String imgPath=UploadFile.doUploadImg(commentImg,null, DateUtil.getYear()+"-"+DateUtil.getMonth() + "-" + DateUtil.getDay());
				feedback.setCommentImg(imgPath);
			}
			feedback.setId(StringUtil.getUUID());
			feedback.setComment(comment);
			feedback.setUserId(userId);
			feedback.setSubmitDate(new Date());
			grssFeedbackService.addByGrssFeedback(feedback);
			return resultSuccess("反馈成功！",feedback);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("反馈异常！");
		}
	}
	
	/**
	 * @comment 根据userids获取users对象
	 * @param token
	 * @param userIds
	 * @return
	 * 2016年2月19日
	 * 上午10:46:10
	 */
	@ResponseBody
	@RequestMapping(value="listUsersByIds",method=RequestMethod.POST)
	public ResultDataTO listUsersByIds(@RequestParam(value = "token",required =false) String token,
			@RequestParam(value = "userIds",required =false) String userIds){
		try {
			List<GrssUser> users=grssUserService.findUsersByIdsStr(userIds);
			if(!StringUtil.empty(token)){
				String loginUserId=SecurityUtil.token2UserId(token);
				List<GrssUser> resultList=new ArrayList<GrssUser>();
				for (int i = 0; i < users.size(); i++) {
					GrssUser user=users.get(i);
					String userId=user.getUserId();
					Integer followRelationship=grssFollowService.findFollowRelationship(loginUserId, userId);
					user.setFollowRelationship(followRelationship);
					resultList.add(user);
				}
				return resultSuccess("获取用户列表成功！", resultList);
			}
			return resultSuccess("获取用户列表成功！", users);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("获取用户列表异常！");
		}
	}
	
	/**
	 * @comment 用户反馈
	 * @param token
	 * @param comment
	 * @return
	 * 2016年2月18日
	 * 下午2:56:52
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/getSysMessage",method=RequestMethod.POST)
	public ResultDataTO getSysMessage(@RequestParam(value = "token",required =false) String token){
		try {
			String userId=SecurityUtil.token2UserId(token);
			List<GrssSysMessage> sysMessages=grssSysMessageService.findMessagesByUserId(userId);
			return resultSuccess("获取系统消息列表成功！",sysMessages);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("获取系统消息列表异常！");
		}
	}
	
	/**
	 * @comment 删除系统消息
	 * @param token
	 * @param comment
	 * @return
	 * 2016年2月18日
	 * 下午2:56:52
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/delSysMessage",method=RequestMethod.POST)
	public ResultDataTO delSysMessage(@RequestParam(value = "token",required =false) String token,@RequestParam(value = "id",required =false) String id){
		try {
			grssSysMessageService.deleteByGrssSysMessage(id);
			return resultSuccess("删除系统消息成功！",null);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("删除系统消息异常！");
		}
	}
	
	/**
	 * @comment 更新用户坐标信息
	 * @param token
	 * @return
	 * 2016年3月3日
	 * 下午5:35:12
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/renewCoordinate",method=RequestMethod.POST)
	public ResultDataTO renewCoordinate(@RequestParam(value = "token",required =false) String token,
			@RequestParam(value = "lng",required =false) String lng,@RequestParam(value = "lat",required =false) String lat){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssUser editUser=new GrssUser();
			editUser.setUserId(userId);
			editUser.setLng(Double.valueOf(lng==null?"0":lng));
			editUser.setLat(Double.valueOf(lat==null?"0":lat));
			grssUserService.updateGrssUser(editUser);
			GrssUser grssUser=grssUserService.findGrssUserByUserId(userId);
			grssUser.setToken(token);
			return resultSuccess("更新坐标信息成功!",grssUser);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild(ParsePropertiesUtil.getMessage("更新坐标信息异常!"));
		}
	}
	/**
	 * 搜索附近的人
	 * @param token 
	 * @param lng 精度
	 * @param lat 纬度
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value = "searchNearbyUser",method = RequestMethod.POST)
	public ResultDataTO searchNearbyUser(@RequestParam(value = "token",required = false) String token,@RequestParam(value = "sex",required = false) String sex,
			@RequestParam(value = "lng",required = false) String lng,@RequestParam(value = "lat",required = false) String lat){
		if(!StringUtil.empty(lng) && !StringUtil.empty(lat)){
			double lngD = Double.parseDouble(lng);
			double latD = Double.parseDouble(lat);
			List<GrssUser> userList = new ArrayList<>();
			String userId = SecurityUtil.token2UserId(token);
			Map<String,Object> params=new HashMap<>();
			params.put("lng", lngD);
			params.put("lat", latD);
			params.put("userId", userId);
			if(!StringUtil.empty(sex)){
				params.put("sex",sex);
			}
			List<GrssUser> grssUsreList = grssUserService.findNearbyUser(params);
			if(grssUsreList != null){
				for(GrssUser user : grssUsreList){
					double distance = KmUtil.getDistance(lngD, latD, user.getLng(), user.getLat());
					if(distance < 3000){
						user.setDistance(distance);
						userList.add(user);
						Collections.sort(userList, new Comparator<GrssUser>(){
							@Override
							public int compare(GrssUser o1, GrssUser o2) {
								return o1.getDistance().compareTo(o2.getDistance());
							}
						});
					}
				}
			}
			return resultSuccess(ParsePropertiesUtil.getMessage("message.success"), userList);
		}
		return resultFaild("获取不到经纬度!");
	}
	
	/**
	 * @comment 获取用户个人设置 及分享链接
	 * @param userId
	 * @return
	 * 2016年3月8日
	 * 上午11:53:27
	 */
	@ResponseBody
	@RequestMapping(value="/getUserSetUp",method=RequestMethod.POST)
	public ResultDataTO getUserSetUp(@RequestParam(value = "userId",required=false) String userId){
		try {
			GrssUserSetup grssUserSetup=grssUserSetupService.findGrssUserSetupByUseId(userId);
			String shareUrl=ParsePropertiesUtil.getIMConfig("APP_SHARE_URL")+userId;
			Map<String,String> shareMap=new HashMap<String, String>();
			shareMap.put("shareUrl", shareUrl);
			List<Object> resultData=new ArrayList<Object>();
			resultData.add(grssUserSetup);
			resultData.add(shareMap);
			return resultSuccess("成功获取用户设置信息及分享链接！",resultData);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return resultFaild("获取用户设置信息及分享链接异常！");
		}
	}
	
	/**
	 * @comment 获取用户自己的优惠券信息
	 * @param token
	 * @return
	 * 2016年3月8日
	 * 下午2:14:07
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/getUserPromos",method=RequestMethod.POST)
	public ResultDataTO getUserPromos(@RequestParam(value="token",required=false)String token){
		try {
			String userId=SecurityUtil.token2UserId(token);
			List<GrssPromo> promos=grssPromoService.findPromosByUserId(userId);
			return resultSuccess("获取优惠券成功！", promos);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("获取优惠券异常！");
		}
	}
	
	/**
	 * @comment 获取用户资产
	 * @param token
	 * @return
	 * 2016年3月11日
	 * 下午2:23:24
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/getUserAssets",method=RequestMethod.POST)
	public ResultDataTO getUserAssets(@RequestParam(value="token",required=false)String token){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssAssets grssAssets=grssAssetsService.findByUserId(userId);
			return resultSuccess("用户资产获取成功！", grssAssets);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("用户资产获取异常！");
		}
	}
	
	/**
	 * @comment 兑换优惠券接口
	 * @param token 登录用户
	 * @param code 优惠码
	 * @return
	 * 2016年3月12日
	 * 下午2:47:43
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/exchangePromo",method=RequestMethod.POST)
	public ResultDataTO exchangePromo(@RequestParam(value="token",required=false)String token,@RequestParam(value="code",required=false)String code){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssPromo grssPromo=grssPromoService.findPromoByCode(code);
			if(grssPromo!=null){
				grssPromo.setType(1);
				grssPromo.setUserId(userId);
				grssPromoService.updateByGrssPromo(grssPromo);
				return resultSuccess("优惠码兑换成功！",grssPromo);
			}else{
				return resultFaild("您填写的优惠码错误或已经过期！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("优惠码兑换异常！");
		}
	}
	
	/**
	 * @comment 提现接口
	 * @param token 用户登录信息
	 * @param money 提现金额
	 * @return
	 * 2016年4月5日
	 * 下午3:31:55
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/drawMoney",method=RequestMethod.POST)
	public ResultDataTO drawMoney(@RequestParam(value="token",required=false)String token,@RequestParam(value="money",required=false)String money){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssUserAccounts userAccounts=grssUserAccountsService.findAccountsByUserId(userId);
			GrssAssets grssAssets=grssAssetsService.findByUserId(userId);
			if(null==userAccounts)
				return resultFaild("请先绑定银行卡信息！");
			Double userMoney=Double.valueOf(grssAssets.getAmount());
			Double drawMoney=Double.valueOf(money);
			if(drawMoney>userMoney)
				return resultFaild("提现金额不能大于账户余额！");
			GrssDrawMoney grssDrawMoney=new GrssDrawMoney();
			grssDrawMoney.setId(StringUtil.getUUID());
			grssDrawMoney.setAccountsId(userAccounts.getId());
			grssDrawMoney.setAmount(new BigDecimal(drawMoney));
			grssDrawMoney.setDrawTime(new Date());
			grssDrawMoneyService.addByGrssDrawMoney(grssDrawMoney,grssAssets);
			return resultSuccess("提现成功，系统为您处理，请耐心等待！", null);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("提现异常！");
		}
	}
	
	/**
	 * @comment 设置/获取用户扩展信息
	 * @param request 
	 * @param token 用户登录凭证
	 * @return
	 * 2016年4月5日
	 * 下午4:18:10
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="/setUserEx",method=RequestMethod.POST)
	public ResultDataTO setUserEx(HttpServletRequest request,@RequestParam(value = "token",required=false) String token,
			@RequestPart(value = "frontImage",required = false) MultipartFile frontImage,
			@RequestPart(value = "sideImage",required = false) MultipartFile sideImage,
			@RequestPart(value = "rearImage",required = false) MultipartFile rearImage){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssUserEx userEx=grssUserExService.findById(userId);
			boolean isAdd=false;
			if(null==userEx)
				isAdd=true;
			userEx=new GrssUserEx(request);
			userEx.setUserId(userId);
			if(frontImage!=null){
				String frontImageUrl="";
				frontImageUrl =UploadFile.doUploadImg(frontImage,null, DateUtil.getYear()+"-"+DateUtil.getMonth() + "-" + DateUtil.getDay());
				userEx.setFrontImageUrl(frontImageUrl);
			}
			if(sideImage!=null){
				String sideImageUrl="";
				sideImageUrl =UploadFile.doUploadImg(sideImage,null, DateUtil.getYear()+"-"+DateUtil.getMonth() + "-" + DateUtil.getDay());
				userEx.setSideImageUrl(sideImageUrl);
			}
			if(rearImage!=null){
				String rearImageUrl="";
				rearImageUrl =UploadFile.doUploadImg(rearImage,null, DateUtil.getYear()+"-"+DateUtil.getMonth() + "-" + DateUtil.getDay());
				userEx.setRearImageUrl(rearImageUrl);
			}
			if(isAdd)
				grssUserExService.addGrssUserEx(userEx);
			else{
				if(!StringUtil.empty(userEx.getAge())||!StringUtil.empty(userEx.getHabitusExp())||!StringUtil.empty(userEx.getHeight())||!StringUtil.empty(userEx.getWeight())||
				!StringUtil.empty(userEx.getHipline())||!StringUtil.empty(userEx.getHabitusExp())||!StringUtil.empty(userEx.getWaist())||!StringUtil.empty(userEx.getHiplineRatio())
				||!StringUtil.empty(userEx.getBodyFat())||!StringUtil.empty(userEx.getMetabolismRatio())||!StringUtil.empty(userEx.getStillHeartbeat())||
				!StringUtil.empty(userEx.getBloodPressure()))
				grssUserExService.updateByGrssUserEx(userEx);
			}
			userEx=grssUserExService.findById(userId);
			return resultSuccess("获取/设置用户扩展信息成功！",userEx);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("获取/设置用户扩展信息异常！");
		}
	}
	
	/**
	 * @comment 根据粉丝数查询达人用户
	 * @return
	 * 2016年5月29日
	 * 下午3:10:44
	 */
	@ResponseBody
	@RequestMapping(value="listHotUsers",method=RequestMethod.POST)
	public ResultDataTO listHotUsers(){
		try {
			List<GrssUser> users=grssUserService.findUsersByHot();
			return resultSuccess("获取达人用户列表成功！", users);
		} catch (Exception e) {
			e.printStackTrace();
			return resultFaild("获取达人用户列表异常！");
		}
	}
}

