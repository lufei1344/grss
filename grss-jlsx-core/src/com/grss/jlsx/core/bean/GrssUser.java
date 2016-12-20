package com.grss.jlsx.core.bean;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.grss.jlsx.core.utils.StringUtil;

public class GrssUser extends BaseBean{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8903662610019924892L;
	private String userId;
	private String userPhone;
	private String userWeibo;
	private String userQQ;
	private String userWeixin;
	private String nikeName;
	private String userPhoto;
	private String password;
	private String userDesc;
	private String userHeight;
	private String userWeight;
	private Integer userSex;
	private String userType;
	private String city;
	private Integer isActivity;
	private Date regDate;
	private Date birthday; 
	private Integer status;
	private String constellation;
	private String coachType;
	private Integer infoComplete;
	private Date regCoachDate;
	private Integer userLevel; //用户等级
	private Double lng;//经度坐标
	private Double lat;//纬度坐标
	private String toClubName;
	
	private Date applyCoachDate;//申请教练资格时间
	
	//以下不属于数据表字段 帮助查询和程序使用
	private Integer followCount;//关注人数
	private Integer fansCount;//粉丝数
	private Integer guidanceCount;//指导人数
	private Double distance; //距离
	private String area;//教练所属场馆区域
	private Integer followRelationship=0;//关注关系 0 未关注 1 已关注 2 互相关注
	private String token;//用户登录token
	
	public GrssUser(){
		
	}
	
	public GrssUser(HttpServletRequest request){
		
	    String userId=request.getParameter("userId");
		if(!StringUtil.empty(userId))
			this.userId = userId;
	
	    String userPhone=request.getParameter("userPhone");
		if(!StringUtil.empty(userPhone))
			this.userPhone = userPhone;
	
	    String userWeibo=request.getParameter("userWeibo");
		if(!StringUtil.empty(userWeibo))
			this.userWeibo = userWeibo;
	
	    String userQq=request.getParameter("userQQ");
		if(!StringUtil.empty(userQq))
			this.userQQ = userQq;
	
	    String userWeixin=request.getParameter("userWeixin");
		if(!StringUtil.empty(userWeixin))
			this.userWeixin = userWeixin;
	
	    String nikeName=request.getParameter("nikeName");
		if(!StringUtil.empty(nikeName))
			this.nikeName = nikeName;
	
	    String userPhoto=request.getParameter("userPhoto");
		if(!StringUtil.empty(userPhoto))
			this.userPhoto = userPhoto;
	
	    String password=request.getParameter("password");
		if(!StringUtil.empty(password))
			this.password = password;
	
	    String userDesc=request.getParameter("userDesc");
		if(!StringUtil.empty(userDesc))
			this.userDesc = userDesc;
	
	    String userHeight=request.getParameter("userHeight");
		if(!StringUtil.empty(userHeight))
			this.userHeight = userHeight;
	
	    String userWeight=request.getParameter("userWeight");
		if(!StringUtil.empty(userWeight))
			this.userWeight = userWeight;
	
	    String userSex=request.getParameter("userSex");
		if(!StringUtil.empty(userSex))
			this.userSex = Integer.parseInt(userSex);
	
	    String userType=request.getParameter("userType");
		if(!StringUtil.empty(userType))
			this.userType = userType;
	
	    String city=request.getParameter("city");
		if(!StringUtil.empty(city))
			this.city = city;
	
	    String isActivity=request.getParameter("isActivity");
		if(!StringUtil.empty(isActivity))
			this.isActivity = Integer.parseInt(isActivity);
	
	    String regDate=request.getParameter("regDate");
		if(!StringUtil.empty(regDate))
			this.regDate = new Date(regDate);
	
	    String birthday=request.getParameter("birthday");
		if(!StringUtil.empty(birthday))
			this.birthday = new Date(birthday);
	
	    String status=request.getParameter("status");
		if(!StringUtil.empty(status))
			this.status = Integer.parseInt(status);
	
	    String constellation=request.getParameter("constellation");
		if(!StringUtil.empty(constellation))
			this.constellation = constellation;
	
	    String coachType=request.getParameter("coachType");
		if(!StringUtil.empty(coachType))
			this.coachType = coachType;
	
	    String infoComplete=request.getParameter("infoComplete");
		if(!StringUtil.empty(infoComplete))
			this.infoComplete = Integer.parseInt(infoComplete);
	}

	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserWeibo() {
		return userWeibo;
	}
	public void setUserWeibo(String userWeibo) {
		this.userWeibo = userWeibo;
	}
	public String getUserQQ() {
		return userQQ;
	}
	public void setUserQQ(String userQQ) {
		this.userQQ = userQQ;
	}
	public String getUserWeixin() {
		return userWeixin;
	}
	public void setUserWeixin(String userWeixin) {
		this.userWeixin = userWeixin;
	}
	public String getNikeName() {
		if(StringUtil.empty(nikeName)){
			return userPhone;
		}
		return nikeName;
	}
	public void setNikeName(String nikeName) {
		this.nikeName = nikeName;
	}
	public String getUserPhoto() {
		return userPhoto;
	}
	public void setUserPhoto(String userPhoto) {
		this.userPhoto = userPhoto;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserDesc() {
		return userDesc;
	}
	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}
	public String getUserHeight() {
		return userHeight;
	}
	public void setUserHeight(String userHeight) {
		this.userHeight = userHeight;
	}
	public String getUserWeight() {
		return userWeight;
	}
	public void setUserWeight(String userWeight) {
		this.userWeight = userWeight;
	}
	public Integer getUserSex() {
		return userSex;
	}
	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getIsActivity() {
		return isActivity;
	}
	public void setIsActivity(Integer isActivity) {
		this.isActivity = isActivity;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getConstellation() {
		return constellation;
	}
	public void setConstellation(String constellation) {
		this.constellation = constellation;
	}
	public String getCoachType() {
		return coachType;
	}
	public void setCoachType(String coachType) {
		this.coachType = coachType;
	}
	public Integer getInfoComplete() {
		return infoComplete;
	}
	public void setInfoComplete(Integer infoComplete) {
		this.infoComplete = infoComplete;
	}
	

	public Date getRegCoachDate() {
		return regCoachDate;
	}

	public void setRegCoachDate(Date regCoachDate) {
		this.regCoachDate = regCoachDate;
	}

	

	public Integer getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(Integer userLevel) {
		this.userLevel = userLevel;
	}

	

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Integer getFollowRelationship() {
		return followRelationship;
	}
	public void setFollowRelationship(Integer followRelationship) {
		this.followRelationship = followRelationship;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

	public Integer getGuidanceCount() {
		if(guidanceCount==null)
			guidanceCount=0;
		return guidanceCount;
	}

	public void setGuidanceCount(Integer guidanceCount) {
		this.guidanceCount = guidanceCount;
	}

	public Double getDistance() {
		return distance;
	}

	public void setDistance(Double distance) {
		this.distance = distance;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public Date getApplyCoachDate() {
		return applyCoachDate;
	}

	public void setApplyCoachDate(Date applyCoachDate) {
		this.applyCoachDate = applyCoachDate;
	}

	public String getToClubName() {
		return toClubName;
	}

	public void setToClubName(String toClubName) {
		this.toClubName = toClubName;
	}
	
	
	
}
