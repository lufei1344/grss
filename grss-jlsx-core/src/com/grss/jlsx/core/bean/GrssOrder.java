package com.grss.jlsx.core.bean;

import java.util.Date;

public class GrssOrder extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5541411096606067369L;

	private String id;

    private String name;

    private String amount;

    private String courseId;

    private String userId;

    private Date payDate;

    private Integer state;

    private Date orderDate;

    private String userComment;
    
    private String coachComment;

    private Integer orderCount;
    
    private GrssUser grssUser;
    
    private GrssUserEx userEx;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount == null ? null : amount.trim();
    }

    public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }


	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public GrssUser getGrssUser() {
		if(grssUser==null){
			grssUser=new GrssUser();
		}
		return grssUser;
	}

	public void setGrssUser(GrssUser grssUser) {
		this.grssUser = grssUser;
	}

	public String getUserComment() {
		return userComment;
	}

	public void setUserComment(String userComment) {
		this.userComment = userComment;
	}

	public String getCoachComment() {
		return coachComment;
	}

	public void setCoachComment(String coachComment) {
		this.coachComment = coachComment;
	}

	public GrssUserEx getUserEx() {
		if(userEx==null)
			userEx=new GrssUserEx();
		return userEx;
	}

	public void setUserEx(GrssUserEx userEx) {
		this.userEx = userEx;
	}
}