package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssUserSetup;

public interface GrssUserSetupService {
    int addGrssUserSetup(GrssUserSetup grssUserSetup);

    GrssUserSetup findGrssUserSetupByUseId(String userId);

    int updateByGrssUserSetup(GrssUserSetup grssUserSetup);
    
}