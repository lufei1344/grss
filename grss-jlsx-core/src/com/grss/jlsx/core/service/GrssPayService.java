package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssPay;

public interface GrssPayService {

	void saveGrssPay(GrssPay grssPay);

	GrssPay findGrssPayById(String payId);

	void updateGrssPay(GrssPay pay);

	GrssPay findGrssPayByOrderId(String orderNo);

}
