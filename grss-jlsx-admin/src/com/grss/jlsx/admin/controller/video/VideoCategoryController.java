package com.grss.jlsx.admin.controller.video;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.core.bean.GrssVideo;
import com.grss.jlsx.core.bean.GrssVideoCatPrograma;
import com.grss.jlsx.core.bean.GrssVideoCategory;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssVideoCatProgramaService;
import com.grss.jlsx.core.service.GrssVideoCategoryService;
import com.grss.jlsx.core.service.GrssVideoService;
import com.grss.jlsx.core.utils.StringUtil;
@Controller
@RequestMapping("/videoCategory/")
public class VideoCategoryController extends BaseController{
	@Resource
	private GrssVideoCategoryService grssVideoCategoryService;
	@Resource
	private GrssVideoCatProgramaService grssVideoCatProgramaService;
	@Resource
	private GrssVideoService grssVideoService;
	
	@RequestMapping("listVideoCategory")
	public String listVideoCategory(HttpServletRequest request,String catName){
		Pager<GrssVideoCategory> pager=grssVideoCategoryService.findCatsList(catName);
		return goTo(request,"video/category/listVideoCategory",pager);
	}
	
	@RequestMapping("addVideoCategory")
	public String addVideoCategory(HttpServletRequest request){
		List<GrssVideoCategory> parentCats=grssVideoCategoryService.findParentCats();
		List<GrssVideoCatPrograma> selectProgramas=grssVideoCatProgramaService.findGrssVideoCatProgramaAll();
		request.setAttribute("parentCats", parentCats);
		request.setAttribute("selectProgramas", selectProgramas);
		return goTo("video/category/addVideoCategory");
	}
	
	@RequestMapping("saveVideoCategory")
	public ModelAndView saveVideoCategory(GrssVideoCategory videoCategory){
		try{
			String parentId=videoCategory.getPrarentId();
			GrssVideoCategory parentCat=grssVideoCategoryService.findCatById(parentId);
			String maxCode=grssVideoCategoryService.findMaxCateCodeByPid(parentId==null?"":parentId);
			String maxCodeAdd=StringUtil.getMaxCodeAdd(maxCode,parentCat.getCateCode());
			videoCategory.setCateCode(maxCodeAdd);
			videoCategory.setCreateDate(new Date());
			videoCategory.setUpdateDate(new Date());
			videoCategory.setId(StringUtil.getUUID());
			grssVideoCategoryService.addVideoCategory(videoCategory);
			return ajaxDoneSuccess("视频分类添加成功！");
		}catch(Exception e){
			e.printStackTrace();
			return ajaxDoneError("视频分类添加失败！");
		}
	}
	
	@RequestMapping("editVideoCategory")
	public String editVideoCategory(HttpServletRequest request,String id){
		GrssVideoCategory videoCategory=grssVideoCategoryService.findCatById(id);
		List<GrssVideoCategory> parentCats=grssVideoCategoryService.findParentCats();
		List<GrssVideoCatPrograma> selectProgramas=grssVideoCatProgramaService.findGrssVideoCatProgramaAll();
		request.setAttribute("parentCats", parentCats);
		request.setAttribute("selectProgramas", selectProgramas);
		request.setAttribute("videoCategory", videoCategory);
		return goTo("video/category/editVideoCategory");
	}
	
	@RequestMapping("updateVideoCategory")
	public ModelAndView updateVideoCategory(GrssVideoCategory videoCategory){
		try {
			videoCategory.setUpdateDate(new Date());
			grssVideoCategoryService.updateVideoCategory(videoCategory);
			return ajaxDoneSuccess("视频分类修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("视频分类修改失败！");
		}
	}
	
	@RequestMapping("deleteVideoCategory")
	public ModelAndView deleteVideoCategory(String id){
		try {
			List<GrssVideoCategory> subCats=grssVideoCategoryService.findCategoryByParentIdList(id);
			List<GrssVideo> catVideos=grssVideoService.findGrssVideoByCatIdList(id);
			if(subCats.size()>0)
				return ajaxDoneError("该视频分类下有子级分类不能删除！");
			if(catVideos.size()>0)
				return ajaxDoneError("该视频分类下有视频不能删除！");
			grssVideoCategoryService.deleteVideoCategory(id);
			return ajaxDoneSuccess("视频分类删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("视频分类删除失败！");
		}
	}
}
