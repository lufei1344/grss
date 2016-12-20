package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.VerifyCode;
import com.grss.jlsx.core.dao.VerifyCodeDao;
import com.grss.jlsx.core.service.VerifyCodeService;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
	@Resource 
	private VerifyCodeDao verifyCodeDao;
	
	
	public int addVerifyCode(VerifyCode verifyCode) {
		return verifyCodeDao.insertVerifyCode(verifyCode);
	}

	public VerifyCode findVerifyCodeByPhone(String phone) {
		return verifyCodeDao.selectByPhone(phone);
	}

	public int updateVerifyCode(VerifyCode verifyCode) {
		return verifyCodeDao.updateVerifyCode(verifyCode);
	}

	public int deleteVerifyCode(String phone) {
		return verifyCodeDao.deleteVerifyCode(phone);
	}

}
