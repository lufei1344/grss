package com.grss.jlsx.core.bean;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.grss.jlsx.core.utils.StringUtil;

public class GrssClub extends BaseBean{
	private static final long serialVersionUID = -4811283688073499669L;
	/**
	 * 基本属性
	 */
	private String clubId;
	private String clubName;
	private String clubImg;
	private String clubTel;
	private String clubDesc;
	private String clubAddress;
	private String province;
	private String city;
	private String area;
	private String longitude;
	private String latitude;
	private java.util.Date createDate;
	private java.util.Date updateDate;
	private String type;
	private String imgs;
	private String clubIco;
	
	private Integer isCollection;
	
	private String clubImgs [];
	
	public GrssClub(){
	}
	
	public GrssClub(HttpServletRequest request){
		
		    String clubId=request.getParameter("clubId");
			if(!StringUtil.empty(clubId))
				this.clubId = clubId;
		
		    String clubName=request.getParameter("clubName");
			if(!StringUtil.empty(clubName))
				this.clubName = clubName;
		
		    String clubImg=request.getParameter("clubImg");
			if(!StringUtil.empty(clubImg))
				this.clubImg = clubImg;
		
		    String clubTel=request.getParameter("clubTel");
			if(!StringUtil.empty(clubTel))
				this.clubTel = clubTel;
		
		    String clubDesc=request.getParameter("clubDesc");
			if(!StringUtil.empty(clubDesc))
				this.clubDesc = clubDesc;
		
		    String clubAddress=request.getParameter("clubAddress");
			if(!StringUtil.empty(clubAddress))
				this.clubAddress = clubAddress;
		
		    String province=request.getParameter("province");
			if(!StringUtil.empty(province))
				this.province = province;
		
		    String city=request.getParameter("city");
			if(!StringUtil.empty(city))
				this.city = city;
		
		    String area=request.getParameter("area");
			if(!StringUtil.empty(area))
				this.area = area;
		
		    String longitude=request.getParameter("longitude");
			if(!StringUtil.empty(longitude))
				this.longitude = longitude;
		
		    String latitude=request.getParameter("latitude");
			if(!StringUtil.empty(latitude))
				this.latitude = latitude;
		
		    String createDate=request.getParameter("createDate");
			if(!StringUtil.empty(createDate))
				this.createDate = new Date(createDate);
		
		    String updateDate=request.getParameter("updateDate");
			if(!StringUtil.empty(updateDate))
				this.updateDate = new Date(updateDate);
			
			String type=request.getParameter("type");
			if(!StringUtil.empty(type))
				this.type = type;
		}

	public GrssClub(String clubName,String clubImg,String clubTel,String clubDesc,String clubAddress,String province,String city,String area,String longitude,String latitude,java.util.Date createDate,java.util.Date updateDate,String type){
		this.clubName = clubName;
		this.clubImg = clubImg;
		this.clubTel = clubTel;
		this.clubDesc = clubDesc;
		this.clubAddress = clubAddress;
		this.province = province;
		this.city = city;
		this.area = area;
		this.longitude = longitude;
		this.latitude = latitude;
		this.createDate = createDate;
		this.updateDate = updateDate;
		this.type = type;
	}

	/***
	 * get AND set 方法
	 * 
	 **/
	public void setClubId(String value) {
		this.clubId = value;
	}
	public String getClubId() {
		return this.clubId;
	}
	public void setClubName(String value) {
		this.clubName = value;
	}
	public String getClubName() {
		return this.clubName;
	}
	public void setClubImg(String value) {
		this.clubImg = value;
	}
	public String getClubImg() {
		return this.clubImg;
	}
	public void setClubTel(String value) {
		this.clubTel = value;
	}
	public String getClubTel() {
		return this.clubTel;
	}
	public void setClubDesc(String value) {
		this.clubDesc = value;
	}
	public String getClubDesc() {
		return this.clubDesc;
	}
	public void setClubAddress(String value) {
		this.clubAddress = value;
	}
	public String getClubAddress() {
		return this.clubAddress;
	}
	public void setProvince(String value) {
		this.province = value;
	}
	public String getProvince() {
		return this.province;
	}
	public void setCity(String value) {
		this.city = value;
	}
	public String getCity() {
		return this.city;
	}
	public void setArea(String value) {
		this.area = value;
	}
	public String getArea() {
		return this.area;
	}
	public void setLongitude(String value) {
		this.longitude = value;
	}
	public String getLongitude() {
		return this.longitude;
	}
	public void setLatitude(String value) {
		this.latitude = value;
	}
	public String getLatitude() {
		return this.latitude;
	}
	public void setCreateDate(java.util.Date value) {
		this.createDate = value;
	}
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	public void setUpdateDate(java.util.Date value) {
		this.updateDate = value;
	}
	public java.util.Date getUpdateDate() {
		return this.updateDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImgs() {
		return imgs;
	}

	public void setImgs(String imgs) {
		if(!StringUtil.empty(imgs)){
			String clubImgs[]=imgs.split(",");
			setClubImgs(clubImgs);
		}
		this.imgs = imgs;
	}

	public Integer getIsCollection() {
		return isCollection;
	}

	public void setIsCollection(Integer isCollection) {
		this.isCollection = isCollection;
	}

	public String[] getClubImgs() {
		return clubImgs==null?new String[]{}:clubImgs;
	}

	public void setClubImgs(String[] clubImgs) {
		this.clubImgs = clubImgs;
	}

	public String getClubIco() {
		return clubIco;
	}

	public void setClubIco(String clubIco) {
		this.clubIco = clubIco;
	}
	
}

