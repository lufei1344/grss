package com.grss.jlsx.admin.controller.video;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.upload.UploadFile;
import com.grss.jlsx.admin.process.vo.VideoParamsVO;
import com.grss.jlsx.core.bean.GrssVideo;
import com.grss.jlsx.core.bean.GrssVideoCategory;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssVideoCategoryService;
import com.grss.jlsx.core.service.GrssVideoOrderService;
import com.grss.jlsx.core.service.GrssVideoService;
import com.grss.jlsx.core.utils.DateUtil;
import com.grss.jlsx.core.utils.StringUtil;
@Controller
@RequestMapping("/video/")
public class VideoController extends BaseController{
	@Resource
	private GrssVideoCategoryService grssVideoCategoryService;
	@Resource
	private GrssVideoService grssVideoService;
	@Resource
	private GrssVideoOrderService grssVideoOrderService;
	
	@RequestMapping("listVideo")
	public String listVideo(HttpServletRequest request,VideoParamsVO paramsVO){
		Map<String,Object> params=paramsVO.getParamsMap();
		Pager<GrssVideo> pager=grssVideoService.findVideosList(params);
		List<GrssVideoCategory> videoCats=grssVideoCategoryService.findAllVideoCats();
		request.setAttribute("videoCats", videoCats);
		return goTo(request,"video/listVideo",pager);
	}
	
	@RequestMapping("addVideo")
	public String addVideo(HttpServletRequest request){
		List<GrssVideoCategory> videoCats=grssVideoCategoryService.findAllVideoCats();
		request.setAttribute("videoCats", videoCats);
		return goTo("video/addVideo");
	}
	
	@RequestMapping("saveVideo")
	public ModelAndView saveVideo(GrssVideo grssVideo,MultipartFile videoImg,MultipartFile videoFile){
		try{
			String catId=grssVideo.getCatId();
			GrssVideoCategory videoCategory=grssVideoCategoryService.findCatById(catId);
			if(videoCategory.getCateCode().length()<8){
				return ajaxDoneError("只能在第三级视频分类添加视频！");
			}
			if(videoImg!=null&&!videoImg.isEmpty()){
				String imgUrl = UploadFile.doUploadImg(videoImg, null,DateUtil.date2Str(new Date()));
				grssVideo.setImgUrl(imgUrl);
			}else{
				return ajaxDoneError("视频主图不能为空！");
			}
			if(videoFile!=null&&!videoFile.isEmpty()){
				String fileUrl= UploadFile.doUploadFile(videoFile, null, DateUtil.date2Str(new Date()));
				grssVideo.setUrl(fileUrl);
			}else{
				return ajaxDoneError("视频不能为空！");
			}
			grssVideo.setUploadDate(new Date());
			grssVideo.setId(StringUtil.getUUID());
			grssVideoService.addVideo(grssVideo);
			return ajaxDoneSuccess("视频添加成功！");
		}catch(Exception e){
			e.printStackTrace();
			return ajaxDoneError("视频添加失败！");
		}
	}
	
	@RequestMapping("editVideo")
	public String editVideo(HttpServletRequest request,String id){
		List<GrssVideoCategory> videoCats=grssVideoCategoryService.findAllVideoCats();
		GrssVideo grssVideo=grssVideoService.findVideoById(id);
		request.setAttribute("videoCats", videoCats);
		request.setAttribute("grssVideo", grssVideo);
		return goTo("video/editVideo");
	}
	
	@RequestMapping("updateVideo")
	public ModelAndView updateVideo(GrssVideo grssVideo,MultipartFile videoImg,MultipartFile videoFile){
		try {
			String catId=grssVideo.getCatId();
			GrssVideoCategory videoCategory=grssVideoCategoryService.findCatById(catId);
			if(videoCategory.getCateCode().length()<8){
				return ajaxDoneError("只能选择第三级视频分类！");
			}
			if(videoImg!=null&&!videoImg.isEmpty()){
				String imgUrl = UploadFile.doUploadImg(videoImg, null,DateUtil.date2Str(new Date()));
				grssVideo.setImgUrl(imgUrl);
			}
			if(videoFile!=null&&!videoFile.isEmpty()){
				String fileUrl= UploadFile.doUploadFile(videoFile, null, DateUtil.date2Str(new Date()));
				grssVideo.setUrl(fileUrl);
			}
			grssVideo.setUpdateDate(new Date());
			grssVideoService.updateVideo(grssVideo);
			return ajaxDoneSuccess("视修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("视频修改失败！");
		}
	}
	
	@RequestMapping("deleteVideo")
	public ModelAndView deleteVideo(String id){
		try {
			Integer count=grssVideoOrderService.findOrderCountByVideoId(id);
			if(count>0){
				return ajaxDoneError("该视频已有关联订单不能删除！");
			}
			grssVideoService.deleteVideo(id);
			return ajaxDoneSuccess("视频删除成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("视频删除失败！");
		}
	}
}
