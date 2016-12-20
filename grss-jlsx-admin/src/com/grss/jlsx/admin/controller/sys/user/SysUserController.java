package com.grss.jlsx.admin.controller.sys.user;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.constants.Constants;
import com.grss.jlsx.admin.process.vo.AdminUserVO;
import com.grss.jlsx.core.bean.GrssAdminUser;
import com.grss.jlsx.core.bean.GrssRole;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssAdminUserService;
import com.grss.jlsx.core.service.GrssRoleService;
import com.grss.jlsx.core.utils.SecurityUtil;
import com.grss.jlsx.core.utils.StringUtil;

@Controller
@RequestMapping("/admin/sys/user")
public class SysUserController extends BaseController{
	
	@Resource
	private GrssAdminUserService grssAdminUserService;
	
	@Resource
	private GrssRoleService grssRoleService;
	
	@RequestMapping("/listSysUser")
	public String listSysUser(HttpServletRequest request, AdminUserVO adminUserVO){
		Pager<GrssAdminUser> pager = grssAdminUserService.findGrssAdminUserList(adminUserVO.getParamsMap());
		return goTo(request, "/sys/user/listSysUser", pager);
	}
	
	@RequestMapping("/addSysUser")
	public String addSysUser(HttpServletRequest request){
		setAttr(request);
		return goTo("/sys/user/addSysUser");
	}
	
	@RequestMapping("/saveSysUser")
	public ModelAndView saveSysUser(GrssAdminUser adminUser){
		try{
			if(adminUser != null && !StringUtil.empty(adminUser.getPassword())){
				adminUser.setPassword(SecurityUtil.MD5(adminUser.getPassword()));
			} else {
				adminUser.setPassword(SecurityUtil.MD5(SecurityUtil.DEFAULT_PASSWORD));
			}
			adminUser.setId(StringUtil.getUUID());
			grssAdminUserService.saveGrssAdminUser(adminUser);
			return ajaxDoneSuccess("添加成功！");
		}catch(Exception e){
			e.printStackTrace();
			return ajaxDoneError("添加失败！");
		}
	}
	
	@RequestMapping("/editSysUser/{userId}")
	public String editSysUser(HttpServletRequest request,@PathVariable("userId") String userId){
		GrssAdminUser adminUser = grssAdminUserService.findGrssAdminUserById(userId);
		setAttr(request);
		request.setAttribute("adminUser", adminUser);
		return goTo("/sys/user/editSysUser");
	}
	
	@RequestMapping("/updateSysUser")
	public ModelAndView updateSysUser(GrssAdminUser adminUser){
		try{
			if(adminUser != null && !StringUtil.empty(adminUser.getPassword())){
				adminUser.setPassword(SecurityUtil.MD5(adminUser.getPassword()));
			} else {
				adminUser.setPassword(null);
			}
			grssAdminUserService.updateGrssAdminUser(adminUser);
			return ajaxDoneSuccess("修改成功！");
		}catch(Exception e){
			return ajaxDoneError("修改失败！");
		}
	}
	
	@RequestMapping("/deleteSysUser/{userId}")
	public ModelAndView deleteSysUser(@PathVariable("userId") String userId){
		try{
			grssAdminUserService.deleteGrssAdminUser(userId);
			return ajaxDoneSuccess("删除成功！");
		}catch(Exception e){
			return ajaxDoneError("删除失败！");
		}
	}
	
	@RequestMapping("myInfoDetail")
	public String myInfoDetail(HttpServletRequest request){
		setAttr(request);
		return goTo("/sys/user/myInfoDetail");
	}
	
	private void setAttr(HttpServletRequest request){
		List<GrssRole> roleList = grssRoleService.findGrssRoleList();
		request.setAttribute("roleList", roleList);
	}
}
