package com.grss.jlsx.api.controller.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.grss.jlsx.api.process.upload.UploadFile;
import com.grss.jlsx.api.process.util.DateUtil;
import com.grss.jlsx.api.process.util.ParsePropertiesUtil;
import com.grss.jlsx.api.to.ResultDataTO;
import com.grss.jlsx.core.bean.GrssCoachAttestation;
import com.grss.jlsx.core.bean.GrssCoachComment;
import com.grss.jlsx.core.bean.GrssCourse;
import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.bean.GrssUserAccounts;
import com.grss.jlsx.core.enums.CoachListType;
import com.grss.jlsx.core.po.GrssUserPo;
import com.grss.jlsx.core.service.GrssCoachAttestationService;
import com.grss.jlsx.core.service.GrssCoachCommentService;
import com.grss.jlsx.core.service.GrssCoachService;
import com.grss.jlsx.core.service.GrssCourseService;
import com.grss.jlsx.core.service.GrssFollowService;
import com.grss.jlsx.core.service.GrssOrderService;
import com.grss.jlsx.core.service.GrssUserAccountsService;
import com.grss.jlsx.core.service.GrssUserService;
import com.grss.jlsx.core.utils.SecurityUtil;
import com.grss.jlsx.core.utils.StringUtil;
/**
 * 教练
 * @author wang
 *
 */
@Controller
@RequestMapping(value="/api",method=RequestMethod.POST)
public class CoachController extends BaseController {
	@Resource
	private GrssCoachService grssCoachService;
	@Resource
	private GrssCourseService grssCourseService;
	@Resource
	private GrssCoachAttestationService grssCoachAttestationService;
	@Resource
	private GrssUserService grssUserService;
	@Resource
	private GrssUserAccountsService grssUserAccountsService;
	@Resource
	private GrssCoachCommentService grssCoachCommentService;
	@Resource
	private GrssOrderService grssOrderService;
	@Resource
	private GrssFollowService grssFollowService;
	/**
	 * 获取教练基本信息
	 * @param id 教练Id
	 * @return
	 */
	@ResponseBody
	//@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="getCoach")
    public ResultDataTO getCoach(@RequestParam(value = "token",required=false) String token,@RequestParam("id")String id){
		GrssUserPo grssUser = grssCoachService.findGrssUserByUserId(id);
		if(grssUser == null)
			return resultFaild("没有该用户信息");
		else{
			Integer guidanceCount=grssCourseService.findGuidanceCount(id);
			grssUser.setGuidanceCount(guidanceCount);
			if(!StringUtil.empty(token)){
				String loginUserId=SecurityUtil.token2UserId(token);
				Integer followRelationship=grssFollowService.findFollowRelationship(loginUserId, id);
				GrssCourse grssCourse= grssCourseService.findByCoachId(id);
				Integer orderCount=grssOrderService.findCoachOrderCount(grssCourse.getId());
				Integer userLevel=grssUserService.findCoachLevel(orderCount);
				Integer fansCount = grssFollowService.findFansCount(id);
				grssUser.setFansCount(fansCount);
				grssUser.setFollowRelationship(followRelationship);
				grssUser.setUserLevel(userLevel);
			}
		}
    	return resultSuccess("成功", grssUser);
    }
	
	/**
	 * @comment 绑定银行卡接口
	 * @param request
	 * @param token
	 * @return
	 * 2016年1月31日
	 * 下午6:29:01
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="/bindingBank",method=RequestMethod.POST)
	public ResultDataTO bindingBank(HttpServletRequest request,@RequestParam(value = "token",required=false) String token){
		String userId=SecurityUtil.token2UserId(token);
		GrssUserAccounts accounts=grssUserAccountsService.findAccountsByUserId(userId);
		String payee=request.getParameter("payee");
		String cardNumber=request.getParameter("cardNumber");
		String bankName=request.getParameter("bankName");
		String bankArea=request.getParameter("bankArea");
		if(StringUtil.empty(payee)||StringUtil.empty(cardNumber)||StringUtil.empty(bankName)||StringUtil.empty(bankArea))
			return resultSuccess(ParsePropertiesUtil.getMessage("message.user.bindingBank.success"),accounts==null?new GrssUserAccounts():accounts);
		if(accounts==null){
			accounts=new GrssUserAccounts();
			String uuid=StringUtil.getUUID();
			accounts.setId(uuid);
			accounts.setUserId(userId);
			accounts.setCardNo(cardNumber);
			accounts.setOpenAccountCity(bankArea);
			accounts.setOpenAccountBank(bankName);
			accounts.setOpenAccountName(payee);
			grssUserAccountsService.addByGrssUserAccounts(accounts);
		}else{
			accounts.setUserId(userId);
			accounts.setCardNo(cardNumber);
			accounts.setOpenAccountCity(bankArea);
			accounts.setOpenAccountBank(bankName);
			accounts.setOpenAccountName(payee);
			grssUserAccountsService.updateByGrssUserAccounts(accounts);
		}
		return resultSuccess(ParsePropertiesUtil.getMessage("message.user.bindingBank.success"),accounts);
	}
	/**
	 * @comment 教练上传身份证接口
	 * @param token
	 * @param identityCardImg
	 * @return
	 * 2016年3月2日
	 * 下午2:13:09
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="/uploadIdentityCardImg",method=RequestMethod.POST)
	public ResultDataTO uploadIdentityCardImg(@RequestParam(value = "token",required=false) String token,@RequestPart(value = "identityCardImg",required = false) MultipartFile identityCardImg){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssCoachAttestation coachAttestation=grssCoachAttestationService.findGcaByCoachId(userId);
			String	identityCardUrl=null;
			Integer isHas=1;
			if(null!=coachAttestation){
				identityCardUrl=coachAttestation.getIdentityCardUrl();
			}else{
				coachAttestation=new GrssCoachAttestation();
				coachAttestation.setId(StringUtil.getUUID());
				coachAttestation.setCoachId(userId);
				isHas=0;
			}
			identityCardUrl=UploadFile.doUploadImg(identityCardImg, identityCardUrl!=null?identityCardUrl.replace(serverPath,""):identityCardUrl, DateUtil.getYear()+"-"+DateUtil.getMonth() + "-" + DateUtil.getDay());
			coachAttestation.setIdentityCardUrl(identityCardUrl);
			coachAttestation.setFinishTime(new Date());
			if(isHas==1)
				grssCoachAttestationService.updateByGrssCoachAttestation(coachAttestation);
			else
				grssCoachAttestationService.addByGrssCoachAttestation(coachAttestation);
			return resultSuccess("教练身份信息上传成功！",coachAttestation);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("教练身份信息上传异常！");
		}
	}
	
	/**
	 * @comment 教练上传资格证书
	 * @param token
	 * @param certificationImg
	 * @return
	 * 2016年3月2日
	 * 下午2:27:50
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="/uploadCertificationImg",method=RequestMethod.POST)
	public ResultDataTO uploadCertificationImg(@RequestParam(value = "token",required=false) String token,
			@RequestPart(value = "certificationImg",required = false) MultipartFile certificationImg){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssCoachAttestation coachAttestation=grssCoachAttestationService.findGcaByCoachId(userId);
			String	certificationUrl=null;
			Integer isHas=1;
			if(null!=coachAttestation){
				certificationUrl=coachAttestation.getCertificationUrl();
			}else{
				coachAttestation=new GrssCoachAttestation();
				coachAttestation.setId(StringUtil.getUUID());
				coachAttestation.setCoachId(userId);
				isHas=0;
			}
			certificationUrl=UploadFile.doUploadImg(certificationImg, certificationUrl!=null?certificationUrl.replace(serverPath,""):certificationUrl, DateUtil.getYear()+"-"+DateUtil.getMonth() + "-" + DateUtil.getDay());
			coachAttestation.setCertificationUrl(certificationUrl);
			coachAttestation.setFinishTime(new Date());
			if(isHas==1)
				grssCoachAttestationService.updateByGrssCoachAttestation(coachAttestation);
			else
				grssCoachAttestationService.addByGrssCoachAttestation(coachAttestation);
			return resultSuccess("教练资格信息上传成功！",coachAttestation);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("教练资格信息上传异常！");
		}
	}
	
	/**
	 * @comment 申请教练资格接口
	 * @param token
	 * @return
	 * 2016年2月5日
	 * 下午5:10:49
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="/applyCoachAualification",method=RequestMethod.POST)
	public ResultDataTO applyCoachAualification(@RequestParam(value = "token",required=false) String token){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssUserAccounts accounts=grssUserAccountsService.findAccountsByUserId(userId);
			if (accounts==null) {
				return resultFaild("请先绑定您的银行卡账户！");
			}
			GrssCoachAttestation coachAttestation=grssCoachAttestationService.findGcaByCoachId(userId);
			if(coachAttestation==null){
				return resultFaild("请上传您的身份证明和教练资格证明！");
			}else{
				if(StringUtil.empty(coachAttestation.getIdentityCardUrl()))
					return resultFaild("请上传您的身份证明！");
				if(StringUtil.empty(coachAttestation.getIdentityCardUrl()))
					return resultFaild("请上传您的教练资格证明！");
				GrssUser grssUser=grssUserService.findGrssUserById(userId);
				grssUser.setStatus(3);
				grssUser.setInfoComplete(1);
				grssUser.setApplyCoachDate(new Date());
				grssUserService.updateGrssUser(grssUser);
				return resultSuccess("申请教练资格成功，请耐心等待审核！",grssUser);
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return resultFaild("申请教练资格异常！");
		}
	}
	
	/**
	 * 场馆教练列表
	 * @param cludId  场馆id 必填
	 * @param type  类别类型 必填  //最热，最新，榜单	hot,newest,top;
	 * sex 性别 非必填  男1 女2
	 * nikeName 名字 模糊查询 非必填
	 * @return
	 */
	@ResponseBody
	//@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value = "listCoachByClub")
	public ResultDataTO listCoachByClub(HttpServletRequest request,@RequestParam(value="clubId",required=false) String clubId, @RequestParam(value="type",required=false) String type) {
		CoachListType listType = CoachListType.valueOf(type==null?CoachListType.top.name():type);
		GrssUser coach = new GrssUser(request);
		List<GrssUserPo> list = new ArrayList<GrssUserPo>();
		switch (listType) {
			case top:
				list = grssCoachService.listByTop(clubId, coach);
				break;
			case hot:
				list = grssCoachService.listByHot(clubId, coach);
				break;
			case newest:
				list = grssCoachService.listByNewest(clubId, coach);
				break;
		}
		List<GrssUserPo> resultList = new ArrayList<GrssUserPo>();
		for (GrssUserPo grssUserPo : list) {
			String userId=grssUserPo.getUserId();
			Integer guidanceCount=grssCourseService.findGuidanceCount(userId);
			grssUserPo.setGuidanceCount(guidanceCount);
			if(grssUserPo.getGrssCourse()!=null&&grssUserPo.getGrssCourse().getId()!=null){
				Integer orderCount=grssOrderService.findCoachOrderCount(grssUserPo.getGrssCourse().getId());
				Integer userLevel=grssUserService.findCoachLevel(orderCount); 
				grssUserPo.setUserLevel(userLevel);
			}
			resultList.add(grssUserPo);
		}
		return resultSuccess("成功", resultList);
	}
	
	/**
	 * @comment 获取或设置教练课程信息
	 * @param token
	 * @param name
	 * @param price
	 * @return
	 * 2016年3月8日
	 * 下午2:47:32
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="setUpCourse")
    public ResultDataTO setUpCourse(@RequestParam(value="token",required=false)String token,
    		@RequestParam(value="name",required=false)String name,@RequestParam(value="price",required=false)String price){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssCourse grssCourse=grssCourseService.findByCoachId(userId);
			int isHas=1;
			if(grssCourse==null){
				grssCourse=new GrssCourse();
				grssCourse.setId(StringUtil.getUUID());
				grssCourse.setCoachId(userId);
				grssCourse.setPrice(new BigDecimal(StringUtil.empty(price) ? "2" : price));
				isHas=0;
			}
			if(!StringUtil.empty(name)||!StringUtil.empty(price)){
				if(!StringUtil.empty(name))
					grssCourse.setName(name);
				if(!StringUtil.empty(price)){
					grssCourse.setPrice(new BigDecimal(price));
				}else{
					grssCourse.setPrice(new BigDecimal("1"));
				}
				if(isHas==1){
					grssCourseService.updateByGrssCourse(grssCourse);
				}else{
					grssCourseService.addByGrssCourse(grssCourse);
				}
			}
			Integer orderCount=grssOrderService.findCoachOrderCount(grssCourse.getId());
			grssCourse.setOrderCount(orderCount);
			return resultSuccess("设置/获取课程信息成功！",grssCourse);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("设置/获取课程信息异常！");
		}
    }
	/**
	 * @comment 评价教练接口
	 * @param token 用户登录token
	 * @param coachId 教练ID
	 * @param comment 评价内容
	 * @return
	 * 2016年3月27日
	 * 下午5:33:27
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove = false)
	@RequestMapping(value="evaluateCoach")
    public ResultDataTO evaluateCoach(@RequestParam(value="token",required=false)String token,@RequestParam(value="coachId",required=false)String coachId,
    		@RequestParam(value="comment",required=false)String comment){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssCoachComment coachComment=new GrssCoachComment();
			coachComment.setUserId(userId);
			coachComment.setCoachId(coachId);
			coachComment.setComment(comment);
			coachComment.setCreateTime(new Date());
			coachComment.setState(1);
			grssCoachCommentService.addCoachComment(coachComment);
			return resultSuccess("评价教练成功！",coachComment);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("评价教练异常！");
		}
    }
	
	/**
	 * @comment 教练评价列表
	 * @param coachId
	 * @return
	 * 2016年3月27日
	 * 下午5:35:09
	 */
	@ResponseBody
	@RequestMapping(value="coachCommentList")
    public ResultDataTO coachCommentList(@RequestParam(value="coachId",required=false)String coachId){
		try {
			List<GrssCoachComment> coachComments=grssCoachCommentService.findCoachCommentList(coachId);
			return resultSuccess("获取教练评价成功！",coachComments);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e);
			return resultFaild("获取教练评价异常！");
		}
    }
}
