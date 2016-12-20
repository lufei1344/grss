package com.grss.jlsx.admin.process.vo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.grss.jlsx.core.utils.StringUtil;

public class ClubVO {
	private String clubName;
	private String startDate;
	private String endDate;
	MultipartFile clubMainImg;
	MultipartFile clubIcoImg;
	List<MultipartFile> images;
	Map<String, MultipartFile> imagesMap;
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public MultipartFile getClubMainImg() {
		return clubMainImg;
	}
	public void setClubMainImg(MultipartFile clubMainImg) {
		this.clubMainImg = clubMainImg;
	}
	public MultipartFile getClubIcoImg() {
		return clubIcoImg;
	}
	public void setClubIcoImg(MultipartFile clubIcoImg) {
		this.clubIcoImg = clubIcoImg;
	}
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	
	public Map<String, MultipartFile> getImagesMap() {
		return imagesMap;
	}
	public void setImagesMap(Map<String, MultipartFile> imagesMap) {
		this.imagesMap = imagesMap;
	}
	public Map<String, Object> getParams(){
		Map<String, Object> params = new HashMap<>();
		if(!StringUtil.empty(this.clubName)){
			params.put("clubName", this.clubName);
		}
		if(!StringUtil.empty(this.startDate)){
			params.put("startDate", this.startDate);
		}
		if(!StringUtil.empty(this.endDate)){
			params.put("endDate", this.endDate);
		}
		return params;
	}
	
	
}
