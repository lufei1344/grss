package com.grss.jlsx.api.controller.club;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grss.jlsx.api.controller.BaseController;
import com.grss.jlsx.api.process.annotation.AuthorityAnnotation;
import com.grss.jlsx.api.to.ResultDataTO;
import com.grss.jlsx.core.bean.GrssClub;
import com.grss.jlsx.core.bean.GrssCollect;
import com.grss.jlsx.core.service.GrssClubService;
import com.grss.jlsx.core.service.GrssCollectService;
import com.grss.jlsx.core.utils.SecurityUtil;
import com.grss.jlsx.core.utils.StringUtil;
/**
 * 前台GrssClub
 * @author 
 *
 */
@Controller
@RequestMapping("/api")
public class ClubController extends BaseController {
	
	@Resource
	private GrssClubService grssClubService;
	@Resource
	private GrssCollectService grssCollectService;
	
	/**
	 * 获取场馆
	 * @param clubId 必填
	 * @param token 必填
	 * @return 数据中包含grssCollect对象 这已经有收藏
	 */
	@ResponseBody
	@RequestMapping(value="/getClub",method = RequestMethod.POST)
	public ResultDataTO getGrssClub(@RequestParam(value="clubId")String clubId,@RequestParam(value="token")String token) {
		try {
			String userId="";
			if(!StringUtil.empty(token))
				userId=SecurityUtil.token2UserId(token);
			GrssClub grssClub = grssClubService.findGrssClubById(clubId,userId);
			if(grssClub == null)
				return resultFaild("没有找到信息");
			return resultSuccess("成功", grssClub);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return resultFaild("获取场馆信息异常");
		}
		
	}
	
	
	
	/**
	 * type = ClubType   准确查询 非必填   'Gym','Swimming','FitStudio','DanceStudio' = 健身房 , 游泳房,健身工作室,舞蹈工作室
	 * area 区域  准确查询  非必填
	 * clubName 场馆名，模糊查询 非必填
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/listByGrssClub",method = RequestMethod.POST)
	public ResultDataTO listGrssClub(HttpServletRequest request,@RequestParam(value="token",required = false)String token) {
		try {
			String userId="";
			if(!StringUtil.empty(token))
				userId=SecurityUtil.token2UserId(token);
			GrssClub grssClub = new GrssClub(request);
			List<GrssClub> list = grssClubService.findAll(grssClub,userId);
			return resultSuccess("成功", list);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return resultFaild("获取场馆信息异常");
		}
	}
	
	
	/*********自定义方法**********/
	/**
	 * 收藏场馆
	 * @param clubId
	 * @param token
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/collectClub",method=RequestMethod.POST)
	public ResultDataTO collectClub(@RequestParam(value="clubId")String clubId,@RequestParam(value="token")String token){
		try {
			String userId=SecurityUtil.token2UserId(token);
			GrssCollect grssCollect= new GrssCollect(userId,clubId);
			int result = grssCollectService.insertByGrssCollect(grssCollect);
			 if(result ==0)
				return resultFaild("失败");
			return resultSuccess("成功", grssCollect);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return resultFaild("收藏场馆异常");
		}
	}
	
	/**
	 * 取消收藏
	 * @param id 收藏id
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/cancelCollect",method=RequestMethod.POST)
	public ResultDataTO cancelCollect(@RequestParam(value="clubId")String clubId,@RequestParam(value="token")String token){
		try {
			String userId=SecurityUtil.token2UserId(token);
			int result = grssCollectService.deleteByIds(clubId,userId);
			GrssCollect grssCollect= new GrssCollect(userId,clubId);
			if(result ==0)
				return resultFaild("失败");
			return resultSuccess("成功", grssCollect);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return resultFaild("取消关注异常");
		}
	}
	
	
	/**
	 * 收藏场馆列表
	 * @param id 收藏id
	 * @return
	 */
	@ResponseBody
	@AuthorityAnnotation(isApprove=false)
	@RequestMapping(value="/collectClubList",method=RequestMethod.POST)
	public ResultDataTO collectClubList(@RequestParam(value="token",required=false)String token){
		try {
			String userId=SecurityUtil.token2UserId(token);
			List<GrssClub> list = grssClubService.findCollectClubs(userId);
			return resultSuccess("获取我的收藏成功！", list);
		} catch (Exception e) {
			log.error(e);
			e.printStackTrace();
			return resultFaild("获取我的收藏异常！");
		}
	}
}
