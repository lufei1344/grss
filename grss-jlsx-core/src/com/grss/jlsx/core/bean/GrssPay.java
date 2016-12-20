package com.grss.jlsx.core.bean;

import java.math.BigDecimal;
import java.util.Date;

public class GrssPay extends BaseBean{
    /**
	 * 
	 */
	private static final long serialVersionUID = -3445366156676887785L;

	private String payId;

    private Integer payChannel;

    private String payAccount;

    private BigDecimal shouldAmount;

    private BigDecimal realAmount;

    private Date payTime;

    private Date payFinishTime;

    private String orderId;

    private String promoId;
    
    private String transactionId;

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId == null ? null : payId.trim();
    }

    public Integer getPayChannel() {
        return payChannel;
    }

    public void setPayChannel(Integer payChannel) {
        this.payChannel = payChannel;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount == null ? null : payAccount.trim();
    }

    public BigDecimal getShouldAmount() {
        return shouldAmount;
    }

    public void setShouldAmount(BigDecimal shouldAmount) {
        this.shouldAmount = shouldAmount;
    }

    public BigDecimal getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(BigDecimal realAmount) {
        this.realAmount = realAmount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getPayFinishTime() {
        return payFinishTime;
    }

    public void setPayFinishTime(Date payFinishTime) {
        this.payFinishTime = payFinishTime;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public String getPromoId() {
        return promoId;
    }

    public void setPromoId(String promoId) {
        this.promoId = promoId == null ? null : promoId.trim();
    }

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
    
    
}