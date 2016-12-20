package com.grss.jlsx.core.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssCollect;

public interface GrssCollectDao {
    int deleteById(String id);

    int insertByGrssCollect(GrssCollect grssCollect);

    GrssCollect selectById(String id);

    int updateByGrssCollect(GrssCollect grssCollect);
    
    GrssCollect findGrssCollect(@Param("clubId")String clubId,@Param("userId")String userId);
    
    int deleteByIds(Map<String,Object> params);

}