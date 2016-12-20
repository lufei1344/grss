package com.grss.jlsx.api.controller.passport;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.grss.jlsx.api.controller.BaseController;
import com.grss.jlsx.api.process.annotation.AuthorityAnnotation;
import com.grss.jlsx.api.process.constant.EndPoints;
import com.grss.jlsx.api.process.enums.GrssUserType;
import com.grss.jlsx.api.process.enums.VerifyCodeType;
import com.grss.jlsx.api.process.util.ParsePropertiesUtil;
import com.grss.jlsx.api.to.ResultDataTO;
import com.grss.jlsx.core.bean.GrssAssets;
import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.bean.GrssUserSetup;
import com.grss.jlsx.core.bean.VerifyCode;
import com.grss.jlsx.core.network.im.EasemobIMChannel;
import com.grss.jlsx.core.network.im.IMChannel;
import com.grss.jlsx.core.network.sms.SmsChannel;
import com.grss.jlsx.core.network.sms.SmsChannelYoungLion;
import com.grss.jlsx.core.service.GrssAssetsService;
import com.grss.jlsx.core.service.GrssUserService;
import com.grss.jlsx.core.service.GrssUserSetupService;
import com.grss.jlsx.core.service.VerifyCodeService;
import com.grss.jlsx.core.utils.DateUtil;
import com.grss.jlsx.core.utils.RandomUtil;
import com.grss.jlsx.core.utils.SecurityUtil;
import com.grss.jlsx.core.utils.StringUtil;

@Controller
@RequestMapping("/api")
public class PassportController extends BaseController{
	@Resource
	private GrssUserService grssUserService;
	@Resource
	private VerifyCodeService verifyCodeService;
	@Resource
	private GrssUserSetupService grssUserSetupService;
	@Resource
	private GrssAssetsService grssAssetsService;
	
	/**
	 * @comment 登录接口
	 * @param request
	 * @param phone
	 * @param password
	 * @return
	 * 2016年1月27日
	 * 下午9:21:39
	 */
	@ResponseBody
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResultDataTO login(HttpServletRequest request,@RequestParam(value = "phone",required =false) String phone,@RequestParam(value = "password",required = false) String password){
		Object obj = doLogin(request, phone, password);
		if(obj instanceof GrssUser){
			return resultSuccess(ParsePropertiesUtil.getMessage("message.user.login.success"), obj);
		}
		return resultFaild(obj.toString());
		
	}
	/**
	 * @comment 注册接口
	 * @return ResultDataTO（结果对象）
	 * @author chenzhigang
	 * 2016年1月27日
	 * 下午2:29:19
	 */
	@ResponseBody
	@RequestMapping(value = "/register",method = RequestMethod.POST)
	public ResultDataTO register(HttpServletRequest request,@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="password",required=false)String password,@RequestParam(value="code",required=false)String code,
			@RequestParam(value="type",required=false)String type){
		GrssUser grssUser=getUserByPhone(phone);
		Map<String, String> map = new HashMap<>();
		IMChannel channel = new EasemobIMChannel();
		if(StringUtil.empty(password)){
			password = SecurityUtil.DEFAULT_PASSWORD;
		}
		if(grssUser!=null){
			return resultFaild(ParsePropertiesUtil.getMessage("message.user.register.invalid"));
		}else{
			grssUser=new GrssUser();
			String userId=StringUtil.getUUID();
			grssUser.setUserId(userId);
			if(!StringUtil.empty(new String[]{phone,password})){
				VerifyCode verifyCode=verifyCodeService.findVerifyCodeByPhone(phone);
				if(verifyCode==null||!verifyCode.getCode().equals(code)){
					return resultFaild(ParsePropertiesUtil.getMessage("message.user.register.code.invalid"));
				}
				grssUser.setUserType(type);
				grssUser.setUserPhone(phone);
				grssUser.setPassword(SecurityUtil.MD5(password));
			}else{
				String qq = request.getParameter("qq"); 
				String weibo = request.getParameter("weibo");
				String weixin = request.getParameter("weixin");
				Map<String,String> accountMap=new HashMap<String,String>();
				if(!StringUtil.empty(qq)){
					accountMap.put("qq", qq);
				}else if(!StringUtil.empty(weibo)){
					accountMap.put("weibo", weibo);
				}else{
					accountMap.put("weixin", weixin);
				}
				String nikeName=request.getParameter("nikeName");
				String photoUrl=request.getParameter("photoUrl");
				GrssUser userOther=grssUserService.findGrssUserByAccount(accountMap);
				if(userOther!=null){  //第三方主键已存在
					userId=userOther.getUserId();
					grssUser.setUserId(userId);
					grssUser.setNikeName(nikeName);
					grssUser.setUserPhoto(photoUrl);
					grssUserService.updateGrssUser(grssUser);
					map.put("username",userId);
					map.put("password", SecurityUtil.MD5(password));
					channel.createNewIMUserSingle(EndPoints.USERS_URL, credential, JSON.toJSONString(map));
					return resultSuccess(ParsePropertiesUtil.getMessage("message.user.login.success"),doLogin(request, phone, password));
				}
				if(!StringUtil.empty(qq)){
					grssUser.setUserQQ(qq);
				}else if(!StringUtil.empty(weixin)){
					grssUser.setUserWeixin(weixin);
				}else{
					grssUser.setUserWeibo(weibo);
				}
				grssUser.setNikeName(nikeName);
				grssUser.setUserPhoto(photoUrl);
				grssUser.setUserType(GrssUserType.user.name());
			}
			GrssUserSetup grssUserSetup=new GrssUserSetup();
			String encryptPassword =SecurityUtil.MD5(password);
			grssUserSetup.setUserId(userId);
			grssUser.setRegDate(new Date());
			grssUser.setPassword(encryptPassword);
			String lng = request.getParameter("lng"); 
			String lat = request.getParameter("lat");
			if(!StringUtil.empty(lng))
			grssUser.setLng(Double.valueOf(lng));
			if(!StringUtil.empty(lat))
			grssUser.setLat(Double.valueOf(lat));
			if(GrssUserType.coach.name().equals(type))
				grssUser.setStatus(0);
			grssUserService.addGrssUser(grssUser);
			grssUserSetupService.addGrssUserSetup(grssUserSetup);
			map.put("username",userId);
			map.put("password", encryptPassword);
			channel.createNewIMUserSingle(EndPoints.USERS_URL, credential, JSON.toJSONString(map));
			return resultSuccess(ParsePropertiesUtil.getMessage("message.user.register.success"),doLogin(request, phone, password));
		}
	}
	
	/**
	 * @comment 忘记密码接口
	 * @param phone 手机号码
	 * @param code 验证码
	 * @param password 密码
	 * @return
	 * 2016年1月27日
	 * 下午4:29:47
	 */
	@ResponseBody
	@RequestMapping(value="/forgetPassword",method=RequestMethod.POST)
	public ResultDataTO forgetPassword(@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="code",required=false)String code,@RequestParam(value="password",required=false)String password){
		GrssUser grssUser=getUserByPhone(phone);
		if(grssUser==null){
			return resultFaild(ParsePropertiesUtil.getMessage("message.user.forgetPassword.failed"));
		}else{
			VerifyCode verifyCode=verifyCodeService.findVerifyCodeByPhone(phone);
			if(!verifyCode.getCode().equals(code)){
				return resultFaild(ParsePropertiesUtil.getMessage("message.user.register.code.invalid"));
			}
			GrssUser grssUserEdit=new GrssUser();
			grssUserEdit.setUserId(grssUser.getUserId());
			String ciphertextPasswrod = SecurityUtil.MD5(password);
			grssUserEdit.setPassword(ciphertextPasswrod);
			grssUserService.updateGrssUser(grssUserEdit);
			updateImPassword(grssUser.getUserId(), ciphertextPasswrod);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.user.forgetPassword.success"), grssUser);
		}
	}
	/**
	 * @comment 修改密码接口
	 * @param token 用户token信息
	 * @param newPassword 新密码
	 * @param odlPassword 原密码
	 * @return
	 * 2016年1月27日
	 * 下午4:44:52
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	public ResultDataTO updatePassword(HttpServletRequest request,@RequestParam(value="token",required=false)String token,
			@RequestParam(value="newPassword",required=false)String newPassword,
			@RequestParam(value="oldPassword",required=false)String oldPassword){
		String userId=SecurityUtil.token2UserId(token);
		GrssUser grssUser=grssUserService.findGrssUserByUserId(userId);
		if(!SecurityUtil.MD5(oldPassword).equals(grssUser.getPassword())){
			return resultFaild(ParsePropertiesUtil.getMessage("message.user.updatePassword.failed"));
		}else{
			String cipherNewPassword = SecurityUtil.MD5(newPassword);
			GrssUser grssUserEdit=new GrssUser();
			grssUserEdit.setUserId(grssUser.getUserId());
			grssUserEdit.setPassword(cipherNewPassword);
			grssUser.setPassword(cipherNewPassword);
			grssUserService.updateGrssUser(grssUserEdit);
			updateImPassword(userId,cipherNewPassword);
			grssUser.setToken(SecurityUtil.token(grssUser.getUserId(), cipherNewPassword));
			return resultSuccess(ParsePropertiesUtil.getMessage("message.user.forgetPassword.success"),doLogin(request, grssUser.getUserPhone(),grssUser.getPassword()));
		}
	}
	private  void updateImPassword(String userId,String newpassword){
		IMChannel channel = new EasemobIMChannel();
		channel.modifyIMUserPasswordWithAdminToken(EndPoints.USERS_URL, credential, userId, newpassword);
	}
	/**
	 * @comment 绑定手机号接口
	 * @param token 用户token信息
	 * @param phone 手机号码
	 * @param password 密码
	 * @param code
	 * @return
	 * 2016年1月28日
	 * 下午1:33:50
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="bindingPhone",method=RequestMethod.POST)
	public ResultDataTO bindingPhone(HttpServletRequest request,@RequestParam(value="token",required=false)String token,@RequestParam(value="phone",required=false)String phone,
			@RequestParam(value="password",required=false)String password,@RequestParam(value="code",required=false)String code){
		GrssUser grssUser=getUserByPhone(phone);
		if(null!=grssUser){
			return resultFaild(ParsePropertiesUtil.getMessage("message.verificationCode."+VerifyCodeType.bind.name()+".failed"));
		}else{
			VerifyCode verifyCode=verifyCodeService.findVerifyCodeByPhone(phone);
			if(verifyCode==null||!verifyCode.getCode().equals(code)){
				return resultFaild(ParsePropertiesUtil.getMessage("message.user.register.code.invalid"));
			}
			String userId=SecurityUtil.token2UserId(token);
			grssUser=grssUserService.findGrssUserByUserId(userId);
			GrssUser editUser=new GrssUser();
			editUser.setUserId(userId);
			editUser.setUserPhone(phone);
			editUser.setPassword(SecurityUtil.MD5(password));
			grssUserService.updateGrssUser(editUser);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.user.binding.success"), doLogin(request, phone, password));
		}
	}
	
	/**
	 * @comment 更换手机绑定
	 * @param token 用户token信息
	 * @param oldPhone 原手机号
	 * @param password 原密码
	 * @param code 验证码
	 * @param newPhone 新手机号
	 * @return
	 * 2016年1月28日
	 * 下午2:02:09
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="changePhone",method=RequestMethod.POST)
	public ResultDataTO changePhone(HttpServletRequest request,@RequestParam(value="token",required=false)String token,@RequestParam(value="oldPhone",required=false)String oldPhone,
			@RequestParam(value="password",required=false)String password,@RequestParam(value="code",required=false)String code,
			@RequestParam(value="newPhone",required=false)String newPhone){
		GrssUser grssUser=getUserByPhone(newPhone);
		if(null!=grssUser){
			return resultFaild(ParsePropertiesUtil.getMessage("message.verificationCode."+VerifyCodeType.cphone.name()+".failed"));
		}else{
			VerifyCode verifyCode=verifyCodeService.findVerifyCodeByPhone(newPhone);
			if(verifyCode==null||!verifyCode.getCode().equals(code)){
				return resultFaild(ParsePropertiesUtil.getMessage("message.user.register.code.invalid"));
			}
			String userId=SecurityUtil.token2UserId(token);
			grssUser=grssUserService.findGrssUserByUserId(userId);
			if(password!=null&&!SecurityUtil.MD5(password).equals(grssUser.getPassword())){
				return resultFaild(ParsePropertiesUtil.getMessage("message.user.updatePassword.failed"));
			}
			if(!grssUser.getUserPhone().equals(oldPhone)){
				return resultFaild(ParsePropertiesUtil.getMessage("message.user.changePhone.failed"));
			}
			GrssUser editUser=new GrssUser();
			editUser.setUserId(userId);
			editUser.setUserPhone(newPhone);
			grssUserService.updateGrssUser(editUser);
			return resultSuccess(ParsePropertiesUtil.getMessage("message.user.changePhone.success"), doLogin(request,newPhone,password));
		}
	}
	
	/**
	 * @comment 请求验证码
	 * @param phone 手机号
	 * @param verificationCode 验证码
	 * @return
	 * 2016年1月27日
	 * 下午4:37:58
	 */
	@ResponseBody
	@RequestMapping(value="/sendVerifyCode",method=RequestMethod.POST)
	public ResultDataTO sendVerifyCode(@RequestParam(value="phone",required=false)String phone,@RequestParam(value="type",required=false)String type){
		String verificationCode=RandomUtil.getRandom(100000,999999)+"";//API接口发送验证码
		if(VerifyCodeType.reg.name().equals(type)||
			VerifyCodeType.bind.name().equals(type)||
			VerifyCodeType.cphone.name().equals(type)){//注册请求//绑定手机号请求//更换手机号
			GrssUser grssUser = getUserByPhone(phone);
			if(grssUser!=null)
				return resultFaild(ParsePropertiesUtil.getMessage("message.verificationCode."+type+".failed"));
		}
		if(VerifyCodeType.fpd.name().equals(type)){
			GrssUser grssUser = getUserByPhone(phone);
			if(grssUser==null)
				return resultFaild(ParsePropertiesUtil.getMessage("message.verificationCode.fpd.bind"));
		}
		VerifyCode verifyCode=verifyCodeService.findVerifyCodeByPhone(phone);
		if(verifyCode!=null){
			verifyCode.setCode(verificationCode);
			verifyCode.setSendDate(DateUtil.getTimestamp());
			verifyCodeService.updateVerifyCode(verifyCode);
		}else{
			verifyCode=new VerifyCode();
			verifyCode.setPhone(phone);
			verifyCode.setCode(verificationCode);
			verifyCode.setSendDate(DateUtil.getTimestamp());
			verifyCodeService.addVerifyCode(verifyCode);
		}
		SmsChannel channel = new SmsChannelYoungLion();
		channel.sendValidateCode(phone, verificationCode);
		verifyCode.setCode("");
		return resultSuccess(ParsePropertiesUtil.getMessage("message.verificationCode.success"), verifyCode);
	}
	
	//根据手机号获取用户信息
	private GrssUser getUserByPhone(String phone){
		Map<String,String> paramsMap=new HashMap<String,String>();
		paramsMap.put("phone", phone);
		return grssUserService.findGrssUserByAccount(paramsMap);
	}
	
	private Object doLogin(HttpServletRequest request,String phone,String password){
		int flag = 0;
		Map<String,String> accountMap = new HashMap<>();
		if(!StringUtil.empty(new String[]{phone,password})){
			flag = 1;
			accountMap.put("phone", phone);
		}else{
			String qq = request.getParameter("qq"); 
			String weibo = request.getParameter("weibo");
			String weixin = request.getParameter("weixin");
			if(!StringUtil.empty(qq)){
				accountMap.put("qq", qq);
			}else if(!StringUtil.empty(weibo)){
				accountMap.put("weibo", weibo);
			}else{
				accountMap.put("weixin", weixin);
			}
			
		}
		if(accountMap.size() <= 0){
			return ParsePropertiesUtil.getMessage("message.params.required.failed");
		}
		GrssUser grssUser =  grssUserService.findGrssUserByAccount(accountMap);
		if(grssUser == null){
			return ParsePropertiesUtil.getMessage("message.username.invalid");
		}
		if(flag == 1 && !SecurityUtil.checkPassword(grssUser.getPassword(), password)){
			return ParsePropertiesUtil.getMessage("message.password.invalid");
		}
		String token = SecurityUtil.token(grssUser.getUserId(), grssUser.getPassword());
		grssUser.setToken(token);
		String lng = request.getParameter("lng"); 
		String lat = request.getParameter("lat");
		if(!StringUtil.empty(lng)||!StringUtil.empty(lat)){
			if(!StringUtil.empty(lng))
			grssUser.setLng(Double.valueOf(lng));
			if(!StringUtil.empty(lat))
			grssUser.setLat(Double.valueOf(lat));
			grssUserService.updateGrssUser(grssUser);
		}
		return grssUser;
	}
}
