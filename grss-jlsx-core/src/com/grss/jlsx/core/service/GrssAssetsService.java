package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssAssets;

public interface GrssAssetsService {
    int deleteById(String id);

    int addByGrssAssets(GrssAssets grssAssets);

    GrssAssets findById(String id);

    int updateByGrssAssets(GrssAssets grssAssets);
    
    GrssAssets findByUserId(String userId);

}