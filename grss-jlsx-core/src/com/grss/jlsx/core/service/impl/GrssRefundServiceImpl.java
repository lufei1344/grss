package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssRefund;
import com.grss.jlsx.core.dao.GrssRefundDao;
import com.grss.jlsx.core.service.GrssRefundService;
@Service("grssRefundService")
public class GrssRefundServiceImpl implements GrssRefundService {
	
	@Resource
	private GrssRefundDao grssRefundDao;
	
	
	@Override
	public void saveRefund(GrssRefund grssRefund) {
		grssRefundDao.insertGrssRefund(grssRefund);
	}

}
