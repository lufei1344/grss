package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.VerifyCode;

/**
 * @comment 验证码Service
 * com.grss.jlsx.core.service
 * VerifyCodeService
 * 2016年1月27日 
 * 下午8:29:51
 * @author chenzg
 */
public interface VerifyCodeService {
	
	int addVerifyCode(VerifyCode verifyCode);
    
	VerifyCode findVerifyCodeByPhone(String phone);

    int updateVerifyCode(VerifyCode verifyCode);
    
    int deleteVerifyCode(String phone);
}
