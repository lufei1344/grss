package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssUserEx;

public interface GrssUserExService {
    int deleteById(String userId);

    int addGrssUserEx(GrssUserEx userEx);

    GrssUserEx findById(String userId);

    int updateByGrssUserEx(GrssUserEx userEx);

}