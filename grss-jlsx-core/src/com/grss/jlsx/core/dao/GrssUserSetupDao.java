package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssUserSetup;

public interface GrssUserSetupDao {
    int insertGrssUserSetup(GrssUserSetup grssUserSetup);

    GrssUserSetup selectGrssUserSetupByUseId(String userId);

    int updateByGrssUserSetup(GrssUserSetup grssUserSetup);
    
}