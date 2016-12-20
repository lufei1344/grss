package com.grss.jlsx.core.service;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssAssets;
import com.grss.jlsx.core.bean.GrssDrawMoney;
import com.grss.jlsx.core.bean.GrssDrawMoneyVO;
import com.grss.jlsx.core.pages.Pager;

public interface GrssDrawMoneyService {
    int deleteById(String id);

    int addByGrssDrawMoney(GrssDrawMoney grssDrawMoney);

    GrssDrawMoney findById(String id);

    int updateByGrssDrawMoney(GrssDrawMoney grssDrawMoney);

    Pager<GrssDrawMoneyVO> findDrawMoneyList(Map<String,Object> params);

	List<GrssDrawMoneyVO> findDrawMoneyData(Map<String, Object> params);

	void addByGrssDrawMoney(GrssDrawMoney grssDrawMoney, GrssAssets grssAssets);
}