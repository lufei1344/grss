package com.grss.jlsx.core.bean;

import java.util.Date;

public class GrssRefund extends BaseBean{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6638852641334710137L;

	private String refundId;
	
	private String refundAmount;
	
	private String refundOrderNo;
	
	private String refundReason;
	
	private int isSuccess;
	
	private Date refundTime;

	public String getRefundId() {
		return refundId;
	}

	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}

	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public String getRefundOrderNo() {
		return refundOrderNo;
	}

	public void setRefundOrderNo(String refundOrderNo) {
		this.refundOrderNo = refundOrderNo;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public int getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(int isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public GrssRefund(String refundId, String refundAmount, String refundOrderNo, String refundReason, int isSuccess,
			Date refundTime) {
		super();
		this.refundId = refundId;
		this.refundAmount = refundAmount;
		this.refundOrderNo = refundOrderNo;
		this.refundReason = refundReason;
		this.isSuccess = isSuccess;
		this.refundTime = refundTime;
	}

	public GrssRefund() {}
	
	
	
}
