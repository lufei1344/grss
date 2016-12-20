package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssDrawMoney;
import com.grss.jlsx.core.bean.GrssDrawMoneyVO;

public interface GrssDrawMoneyDao {
    int deleteById(String id);

    int insertByGrssDrawMoney(GrssDrawMoney grssDrawMoney);

    GrssDrawMoney selectById(String id);

    int updateByGrssDrawMoney(GrssDrawMoney grssDrawMoney);

	List<GrssDrawMoneyVO> selectDrawMoneyList(Map<String, Object> params);

	Integer selectDrawMoneyListCount(Map<String, Object> params);

	List<GrssDrawMoneyVO> selectDrawMoneyData(Map<String, Object> params);

}