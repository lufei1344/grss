package com.grss.jlsx.admin.controller.audit;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.vo.CoachVO;
import com.grss.jlsx.core.bean.GrssCoachAttestation;
import com.grss.jlsx.core.bean.GrssUser;
import com.grss.jlsx.core.bean.GrssUserAccounts;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssCoachAttestationService;
import com.grss.jlsx.core.service.GrssUserAccountsService;
import com.grss.jlsx.core.service.GrssUserService;
import com.grss.jlsx.core.utils.StringUtil;
@Controller
@RequestMapping("/admin/audit")
public class AuditController extends BaseController {
	
	@Resource
	private GrssUserService grssUserService;
	@Resource
	private GrssCoachAttestationService grssCoachAttestationService;
	@Resource
	private GrssUserAccountsService grssUserAccountsService;
	
	
	@RequestMapping("/listAudit")
	public String listAudit(HttpServletRequest request,CoachVO coachVO){
		Pager<GrssUser> page = grssUserService.findGrssUserAudit(coachVO.getParams());
		return goTo(request, "audit/listAudit", page);
	}
	@RequestMapping("/detailAudit/{userId}")
	public String detailAudit(HttpServletRequest request,@PathVariable("userId") String userId){
		GrssCoachAttestation coachAttrstation = grssCoachAttestationService.findGcaByCoachId(userId);
		GrssUserAccounts userAccount = grssUserAccountsService.findAccountsByUserId(userId);
		GrssUser user = grssUserService.findGrssUserById(userId);
		request.setAttribute("coachAttrstation", coachAttrstation);
		request.setAttribute("userAccount", userAccount);
		request.setAttribute("user", user);
		return goTo("audit/detailAudit");
	}
	@RequestMapping("/updateAudit")
	public ModelAndView updateAudit(HttpServletRequest request,String userId,String isPass){
		try{
		if(!StringUtil.empty(isPass)){
			if("1".equals(isPass)){
				GrssUser user = grssUserService.findGrssUserById(userId);
				user.setUserType("coach");
				user.setStatus(1);
				user.setRegCoachDate(new Date());
				grssUserService.updateGrssUser(user);
			}
		}
		return ajaxDoneSuccess("操作成功！");
		}catch(Exception e){
			return ajaxDoneError("操作失败！");
		}
	}
	
	
}
