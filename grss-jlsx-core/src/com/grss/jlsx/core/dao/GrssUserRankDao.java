package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssUserRank;

public interface GrssUserRankDao {
    int deleteByPrimaryKey(Integer rankId);

    int insert(GrssUserRank record);

    int insertSelective(GrssUserRank record);

    GrssUserRank selectByPrimaryKey(Integer rankId);

    int updateByPrimaryKeySelective(GrssUserRank record);

    int updateByPrimaryKey(GrssUserRank record);
}