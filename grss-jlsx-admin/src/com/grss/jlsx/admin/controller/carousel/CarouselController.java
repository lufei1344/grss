package com.grss.jlsx.admin.controller.carousel;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.upload.UploadFile;
import com.grss.jlsx.admin.process.vo.CarouselParamsVO;
import com.grss.jlsx.core.bean.GrssHomeInfo;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssHomeInfoService;
import com.grss.jlsx.core.utils.DateUtil;
@Controller
@RequestMapping("/carousel/")
public class CarouselController extends BaseController{
	@Resource
	private GrssHomeInfoService grssHomeInfoService;
	
	@RequestMapping("listCarousel")
	public String listCarousel(HttpServletRequest request,CarouselParamsVO paramsVO){
		Map<String,Object> params=paramsVO.getParamsMap();
		Pager<GrssHomeInfo> pager=grssHomeInfoService.findHomeInfosList(params);
		return goTo(request,"carousel/listCarousel",pager);
	}
	
    @RequestMapping("addCarousel")
	public String addCarousel(HttpServletRequest request){
		return goTo("carousel/addCarousel");
	}
	
	@RequestMapping("saveCarousel")
	public ModelAndView saveCarousel(GrssHomeInfo grssHomeInfo,MultipartFile carouselImg){
		try{
			if(carouselImg!=null&&!carouselImg.isEmpty()){
				String imgUrl = UploadFile.doUploadImg(carouselImg, null,DateUtil.date2Str(new Date()));
				grssHomeInfo.setImageUrl(imgUrl);
			}else{
				return ajaxDoneError("资讯图片不能为空！");
			}
			grssHomeInfo.setUpdateTime(new Date());
			grssHomeInfoService.addHomeInfo(grssHomeInfo);
			return ajaxDoneSuccess("资讯添加成功！");
		}catch(Exception e){
			e.printStackTrace();
			return ajaxDoneError("资讯添加失败！");
		}
	}
	
	@RequestMapping("editCarousel")
	public String editCarousel(HttpServletRequest request,Integer id){
		GrssHomeInfo grssHomeInfo=grssHomeInfoService.findHomeInfoById(id);
		request.setAttribute("grssHomeInfo", grssHomeInfo);
		return goTo("carousel/editCarousel");
	}
	
	@RequestMapping("updateCarousel")
	public ModelAndView updateCarousel(GrssHomeInfo grssHomeInfo,MultipartFile carouselImg){
		try {
			if(carouselImg!=null&&!carouselImg.isEmpty()){
				String imgUrl = UploadFile.doUploadImg(carouselImg, null,DateUtil.date2Str(new Date()));
				grssHomeInfo.setImageUrl(imgUrl);
			}
			grssHomeInfo.setUpdateTime(new Date());
			grssHomeInfoService.updateCarousel(grssHomeInfo);
			return ajaxDoneSuccess("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("修改失败！");
		}
	}
	
	@RequestMapping("deleteCarousel")
	public ModelAndView deleteCarousel(Integer id){
		try {
			grssHomeInfoService.deleteCarousel(id);
			return ajaxDoneSuccess("删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("删除失败！");
		}
	}
	
	@RequestMapping("showCarousel")
	public ModelAndView showCarousel(GrssHomeInfo grssHomeInfo){
		try {
			Integer isShow=grssHomeInfo.getIsShow();
			switch (isShow) {
			case 1:
				isShow=0;
				break;
			default:
				isShow=1;
				break;
			}
			grssHomeInfo.setIsShow(isShow);
			grssHomeInfoService.updateCarousel(grssHomeInfo);
			return ajaxDoneSuccess("操作成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("显示状态修改失败！");
		}
	}
}
