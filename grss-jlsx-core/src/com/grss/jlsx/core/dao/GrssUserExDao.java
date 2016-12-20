package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssUserEx;

public interface GrssUserExDao {
    int deleteById(String userId);

    int insertGrssUserEx(GrssUserEx userEx);

    GrssUserEx selectById(String userId);

    int updateByGrssUserEx(GrssUserEx userEx);

}