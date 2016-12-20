package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssClubCoach;

public interface GrssClubCoachDao {
    int deleteById(Integer id);
    
    int insertByGrssClubCoach(GrssClubCoach grssClubCoach);

    GrssClubCoach selectById(Integer id);

    int updateByGrssClubCoach(GrssClubCoach grssClubCoach);

}