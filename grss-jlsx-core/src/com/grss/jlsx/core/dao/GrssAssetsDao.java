package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssAssets;

public interface GrssAssetsDao {
    int deleteById(String id);

    int insertByGrssAssets(GrssAssets grssAssets);

    GrssAssets selectById(String id);

    int updateByGrssAssets(GrssAssets grssAssets);
    
    GrssAssets selectByUserId(String userId);

}