package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssPay;
import com.grss.jlsx.core.dao.GrssPayDao;
import com.grss.jlsx.core.service.GrssPayService;
@Service("grssPayService")
public class GrssPayServiceImpl implements GrssPayService {
	@Resource
	private GrssPayDao GrssPayDao;
	
	@Override
	public void saveGrssPay(GrssPay grssPay) {
		GrssPayDao.insertByGrssPay(grssPay);
	}

	@Override
	public GrssPay findGrssPayById(String payId) {
		return GrssPayDao.selectByPayId(payId);
	}

	@Override
	public void updateGrssPay(GrssPay pay) {
		GrssPayDao.updateByGrssPay(pay);
	}

	@Override
	public GrssPay findGrssPayByOrderId(String orderNo) {
		return GrssPayDao.selectGrssPayByOrderId(orderNo);
	}

}
