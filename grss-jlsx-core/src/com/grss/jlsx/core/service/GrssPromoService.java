package com.grss.jlsx.core.service;

import java.util.List;

import com.grss.jlsx.core.bean.GrssPromo;

public interface GrssPromoService {
    int deletePromoById(String id);

    int addByGrssPromo(GrssPromo grssPromo);

    GrssPromo findPromoById(String id);

    int updateByGrssPromo(GrssPromo grssPromo);

    List<GrssPromo> findPromosByUserId(String userId);

	GrssPromo findPromoByCode(String code);
}