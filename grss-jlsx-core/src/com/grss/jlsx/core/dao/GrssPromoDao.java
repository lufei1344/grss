package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssPromo;

public interface GrssPromoDao {
    int deleteById(String id);

    int insertByGrssPromo(GrssPromo grssPromo);

    GrssPromo selectById(String id);

    int updateByGrssPromo(GrssPromo grssPromo);

    List<GrssPromo> selectByUserId(Map<String, Object> params);
    
    GrssPromo selectByCode(String code);
}