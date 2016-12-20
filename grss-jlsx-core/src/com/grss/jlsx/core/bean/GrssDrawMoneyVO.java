package com.grss.jlsx.core.bean;

public class GrssDrawMoneyVO extends GrssDrawMoney{
	private static final long serialVersionUID = -4192766603027637837L;
	
	private String cardNo;
	private String openAccountName;
	private String openAccountCity;
	private String openAccountBank;
	private String userPhone;
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getOpenAccountName() {
		return openAccountName;
	}
	public void setOpenAccountName(String openAccountName) {
		this.openAccountName = openAccountName;
	}
	public String getOpenAccountCity() {
		return openAccountCity;
	}
	public void setOpenAccountCity(String openAccountCity) {
		this.openAccountCity = openAccountCity;
	}
	public String getOpenAccountBank() {
		return openAccountBank;
	}
	public void setOpenAccountBank(String openAccountBank) {
		this.openAccountBank = openAccountBank;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	
	
}