package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssDictionaries;

public interface GrssDictionariesDao {
    int deleteById(Integer id);

    int insertByGrssDictionaries(GrssDictionaries grssDictionaries);

    GrssDictionaries selectById(Integer id);

    int updateByGrssDictionaries(GrssDictionaries grssDictionaries);
}