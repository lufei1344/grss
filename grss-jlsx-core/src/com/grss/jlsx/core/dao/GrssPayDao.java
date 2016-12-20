package com.grss.jlsx.core.dao;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssPay;

public interface GrssPayDao {
    int deleteByPayId(String payId);

    int insertByGrssPay(GrssPay record);

    GrssPay selectByPayId(String payId);

    int updateByGrssPay(GrssPay record);

	GrssPay selectGrssPayByOrderId(@Param("orderId") String orderId);

}