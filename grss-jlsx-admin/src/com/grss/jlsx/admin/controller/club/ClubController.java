package com.grss.jlsx.admin.controller.club;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import com.grss.jlsx.admin.controller.BaseController;
import com.grss.jlsx.admin.process.constants.Constants;
import com.grss.jlsx.admin.process.upload.UploadFile;
import com.grss.jlsx.admin.process.util.ParsePropertiesUtil;
import com.grss.jlsx.admin.process.vo.ClubVO;
import com.grss.jlsx.core.bean.GrssAdminUser;
import com.grss.jlsx.core.bean.GrssClub;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssClubService;
import com.grss.jlsx.core.utils.RestClientUtil;
import com.grss.jlsx.core.utils.StringUtil;
@Controller
@RequestMapping("/admin/club")
public class ClubController extends BaseController{
	@Resource
	private GrssClubService grssClubService;
	
	
	
	@RequestMapping("/listClub")
	public String listClub(HttpServletRequest request,ClubVO clubVO){
		GrssAdminUser adminUser = getCurrentLoginUser(request);
		Map<String, Object> params = clubVO.getParams();
		if(adminUser != null && adminUser.getGrssRole() != null && "CLUB".equals(adminUser.getGrssRole().getRoleType())){
			params.put("adminUserId", adminUser.getId());
		}
		Pager<GrssClub> pager = grssClubService.findGrssClubAllList(params);
		return goTo(request,"club/listClub",pager);
	}
	
	@RequestMapping("/addClub")
	public String addClub(){
		return goTo("club/addClub");
	}
	@RequestMapping("/saveClub")
	public ModelAndView saveClub(HttpServletRequest request,GrssClub club,ClubVO clubVO){
		try{
			GrssAdminUser adminUser = getCurrentLoginUser(request);
			club = setClubParams(club, clubVO);
			club.setClubId(StringUtil.getUUID());
			club.setCreateDate(new Date());
			grssClubService.insertByGrssClub(club,adminUser);
			return ajaxDoneSuccess("添加成功！");
		}catch(Exception e){
			e.printStackTrace();
			return ajaxDoneError("添加失败");
		}
	}
	private GrssClub setClubParams(GrssClub club,ClubVO clubVO) throws Exception{
		Map<String, MultipartFile> imagesMap = clubVO.getImagesMap();
		StringBuilder builder = new StringBuilder();
		if(imagesMap != null){
			GrssClub clubO = grssClubService.findGrssClubById(club.getClubId());
			for(String key : imagesMap.keySet()){
				boolean flag = true;
				if(clubO.getClubImgs() != null){
					for(String imgkey : clubO.getClubImgs()){
						if(StringUtil.trim(key).equals(StringUtil.trim(imgkey))){
							MultipartFile file = imagesMap.get(key);
							if(file != null && !file.isEmpty()){
								String image = UploadFile.doUploadImg(file, null, com.grss.jlsx.core.utils.DateUtil.date2Str(new Date()));
								builder.append(image+",");
							}else{
								builder.append(key+",");
							}
							flag = false;
							break;
						}	
					}
				}
				if(flag){
					MultipartFile file = imagesMap.get(key);
					if(file != null && !file.isEmpty()){
						String image = UploadFile.doUploadImg(file, null, com.grss.jlsx.core.utils.DateUtil.date2Str(new Date()));
						builder.append(image+",");
					}
				}
			}
			if(builder.length() > 0){
				club.setImgs(builder.toString());
			}
		}
		if(clubVO.getClubIcoImg() != null && !clubVO.getClubIcoImg().isEmpty()){
			String icoImg = UploadFile.doUploadImg(clubVO.getClubMainImg(), null, com.grss.jlsx.core.utils.DateUtil.date2Str(new Date()));
			club.setClubIco(icoImg);
		}
		if(clubVO.getClubMainImg() != null && !clubVO.getClubMainImg().isEmpty()){
			String mianImg = UploadFile.doUploadImg(clubVO.getClubMainImg(), null, com.grss.jlsx.core.utils.DateUtil.date2Str(new Date()));
			club.setClubImg(mianImg);
		}
		if(clubVO.getImages() != null){
			StringBuilder stringBuilder = new StringBuilder();
			for(MultipartFile file : clubVO.getImages()){
				if(file != null && !file.isEmpty()){
					String images = UploadFile.doUploadImg(file, null, com.grss.jlsx.core.utils.DateUtil.date2Str(new Date()));
					stringBuilder.append(images + ",");
				}
			}
			if(stringBuilder.length() > 0){
				club.setImgs(stringBuilder.toString());
			}
		}
		
		String lat = null;
		String lng = null;
		String addressUrl = ParsePropertiesUtil.getMapKey("address_find");
		addressUrl = addressUrl.replace("{address}", club.getClubAddress()).replace("{city}", club.getCity());
		String text1 = RestClientUtil.get(addressUrl);
		
		if(!StringUtil.empty(text1)){
			JSONObject jobj = JSON.parseObject(text1);
			if(null != jobj && jobj.getInteger("status") == 0){
				JSONObject jdata = jobj.getJSONObject("result");
				JSONObject json = jdata.getJSONObject("location");
				lat = json.get("lat").toString();
				lng = json.get("lng").toString();
			}else{
			String queryUrl = ParsePropertiesUtil.getMapKey("quety_condition");
			queryUrl = queryUrl.replace("{condition}", StringUtil.encodingToUTF8(club.getClubName())).replace("{city}", StringUtil.encodingToUTF8(club.getCity()));
			String text = RestClientUtil.get(queryUrl);
			JSONObject jo = JSON.parseObject(text);
			JSONArray datas = jo.getJSONArray("results");
			if(datas != null && datas.size() > 0){
				JSONObject jdata = JSONObject.parseObject(datas.get(0).toString());
				JSONObject json = jdata.getJSONObject("location");
				lat = json.get("lat").toString();
				lng = json.get("lng").toString();
				
			}
		}
	}
		club.setLatitude(lat);
		club.setLongitude(lng);
		return club;
	}
	@RequestMapping("/editClub/{clubId}")
	public String editClub(HttpServletRequest request,@PathVariable("clubId") String clubId){
		GrssClub club = grssClubService.findGrssClubById(clubId);
		request.setAttribute("club", club);
		request.setAttribute("imgsLength", club.getClubImgs() == null ? 0 : club.getClubImgs().length);
		return goTo("club/editClub");
	}
	
	@RequestMapping("/updateClub")
	public ModelAndView updateClub(GrssClub club,ClubVO clubVO){
		try {
			club = setClubParams(club, clubVO);
			club.setUpdateDate(new Date());
			grssClubService.updateByGrssClub(club);
			return ajaxDoneSuccess("修改成功！");
		} catch (Exception e) {
			e.printStackTrace();
			return ajaxDoneError("修改失败！");
		}
	}
	@RequestMapping("/deleteClub/{clubId}")
	public ModelAndView deleteClub(HttpServletRequest request,@PathVariable("clubId") String clubId){
		try{
			grssClubService.deleteByGrssClub(clubId);
			return ajaxDoneSuccess("删除成功！");
		}catch(Exception e){
			e.printStackTrace();
			return ajaxDoneError("删除失败！");
		}
	}
}
