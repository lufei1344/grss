package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssUserAccounts;

public interface GrssUserAccountsService {
    int deleteById(String id);

    int addByGrssUserAccounts(GrssUserAccounts accounts);

    GrssUserAccounts findAccountsById(String id);

    int updateByGrssUserAccounts(GrssUserAccounts accounts);
    
    GrssUserAccounts findAccountsByUserId(String userId);
    
}