package com.grss.jlsx.core.bean;

import javax.servlet.http.HttpServletRequest;

import com.grss.jlsx.core.utils.StringUtil;

public class GrssUserEx extends BaseBean{
	private static final long serialVersionUID = 1442338713070428362L;

	private String userId;

    private String age;

    private Integer sex;

    private String height;

    private String weight;

    private String habitusExp;

    private String waist;
    
    private String hipline;
    
    private String hiplineRatio;

    private String bodyFat;

    private String metabolismRatio;

    private String stillHeartbeat;

    private String bloodPressure;
    
    private String frontImageUrl;//正面体位照片
    
    private String sideImageUrl;//侧面体位图片地址
    
    private String rearImageUrl;//背面体位图片地址
    
    
    public GrssUserEx() {
		super();
	}
    
    public GrssUserEx(HttpServletRequest request) {
	   String age=request.getParameter("age");
	   if(!StringUtil.empty(age))
			this.age = age;
	   
	   String sex=request.getParameter("sex");
	   if(!StringUtil.empty(sex))
			this.sex = Integer.parseInt(sex);
	   
	   String height=request.getParameter("height");
	   if(!StringUtil.empty(height))
			this.height = height;
	   
	   String weight=request.getParameter("weight");
	   if(!StringUtil.empty(weight))
			this.weight = weight;

	   String hipline=request.getParameter("hipline");
	   if(!StringUtil.empty(hipline))
			this.hipline = hipline;
	   
	   String habitusExp=request.getParameter("habitusExp");
	   if(!StringUtil.empty(habitusExp))
			this.habitusExp = habitusExp;

	   String waist=request.getParameter("waist");
	   if(!StringUtil.empty(waist))
			this.waist = waist;

	   String hiplineRatio=request.getParameter("hiplineRatio");
	   if(!StringUtil.empty(hiplineRatio))
			this.hiplineRatio = hiplineRatio;
	   
	   String bodyFat=request.getParameter("bodyFat");
	   if(!StringUtil.empty(bodyFat))
			this.bodyFat = bodyFat;

	   String metabolismRatio=request.getParameter("metabolismRatio");
	   if(!StringUtil.empty(metabolismRatio))
			this.metabolismRatio = metabolismRatio;

	   String stillHeartbeat=request.getParameter("stillHeartbeat");
	   if(!StringUtil.empty(stillHeartbeat))
			this.stillHeartbeat = stillHeartbeat;

	   String bloodPressure=request.getParameter("bloodPressure");
	   if(!StringUtil.empty(bloodPressure))
			this.bloodPressure = bloodPressure;
	}
    
	public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age == null ? null : age.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height == null ? null : height.trim();
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight == null ? null : weight.trim();
    }

    public String getHabitusExp() {
        return habitusExp;
    }

    public void setHabitusExp(String habitusExp) {
        this.habitusExp = habitusExp == null ? null : habitusExp.trim();
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist == null ? null : waist.trim();
    }

    public String getHiplineRatio() {
        return hiplineRatio;
    }

    public void setHiplineRatio(String hiplineRatio) {
        this.hiplineRatio = hiplineRatio == null ? null : hiplineRatio.trim();
    }

    public String getBodyFat() {
        return bodyFat;
    }

    public void setBodyFat(String bodyFat) {
        this.bodyFat = bodyFat == null ? null : bodyFat.trim();
    }

    public String getMetabolismRatio() {
        return metabolismRatio;
    }

    public void setMetabolismRatio(String metabolismRatio) {
        this.metabolismRatio = metabolismRatio == null ? null : metabolismRatio.trim();
    }

    public String getStillHeartbeat() {
        return stillHeartbeat;
    }

    public void setStillHeartbeat(String stillHeartbeat) {
        this.stillHeartbeat = stillHeartbeat == null ? null : stillHeartbeat.trim();
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure == null ? null : bloodPressure.trim();
    }

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getHipline() {
		return hipline;
	}

	public void setHipline(String hipline) {
		this.hipline = hipline;
	}

	public String getFrontImageUrl() {
		return frontImageUrl;
	}

	public void setFrontImageUrl(String frontImageUrl) {
		this.frontImageUrl = frontImageUrl;
	}

	public String getSideImageUrl() {
		return sideImageUrl;
	}

	public void setSideImageUrl(String sideImageUrl) {
		this.sideImageUrl = sideImageUrl;
	}

	public String getRearImageUrl() {
		return rearImageUrl;
	}

	public void setRearImageUrl(String rearImageUrl) {
		this.rearImageUrl = rearImageUrl;
	}
}