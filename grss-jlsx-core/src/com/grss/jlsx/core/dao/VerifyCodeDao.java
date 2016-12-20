package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.VerifyCode;

public interface VerifyCodeDao {
    int insertVerifyCode(VerifyCode verifyCode);

    VerifyCode selectByPhone(String phone);

    int updateVerifyCode(VerifyCode verifyCode);
    
    int deleteVerifyCode(String phone);
}