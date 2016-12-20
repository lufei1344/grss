package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssUserAccounts;

public interface GrssUserAccountsDao {
    int deleteById(String id);

    int insertByGrssUserAccounts(GrssUserAccounts record);

    GrssUserAccounts selectById(String id);
    
    GrssUserAccounts selectByUserId(String userId);

    int updateByGrssUserAccounts(GrssUserAccounts record);
}