package com.grss.jlsx.core.bean;

import java.math.BigDecimal;

public class GrssCourse extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = 57476840267976269L;

	private String id;

    private String name;

    private BigDecimal price;

    private String coachId;
    
    private Integer orderCount;//订单总数
    
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

    public BigDecimal getPrice() {
    	if(price==null){
    		return new BigDecimal("1");
    	}
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId == null ? null : coachId.trim();
    }

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}
}