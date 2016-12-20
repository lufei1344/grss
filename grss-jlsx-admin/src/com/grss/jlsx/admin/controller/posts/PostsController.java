package com.grss.jlsx.admin.controller.posts;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.vo.PostsVO;
import com.grss.jlsx.core.bean.GrssClub;
import com.grss.jlsx.core.bean.GrssPosts;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssPostsService;

/**
 * 帖子管理
 * @author wang
 *
 */
@Controller
@RequestMapping("/posts")
public class PostsController extends BaseController{
	
	@Resource
	private GrssPostsService grssPostsService;
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request,PostsVO postsVO){
		Pager<GrssPosts> list = grssPostsService.findGrssPost(postsVO.getParams());
		
		return goTo(request,"posts/list",list);
	}
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(HttpServletRequest request,@PathVariable("id") String id){
		
		grssPostsService.deleteGrssPostsById(id);
		return ajaxDoneSuccess("删除成功");
	}
	
	@RequestMapping("/edit/{id}")
	public String editClub(HttpServletRequest request,@PathVariable("id") String id){
		GrssPosts posts = grssPostsService.findGrssPostsById(id);
		request.setAttribute("posts", posts);
		return goTo("posts/edit");
	}
	
}
