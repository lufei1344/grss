package com.grss.jlsx.core.dao;

import java.util.List;

import com.grss.jlsx.core.bean.GrssCourse;

public interface GrssCourseDao {
    int deleteById(String id);

    int insertByGrssCourse(GrssCourse grssCourse);

    GrssCourse selectById(String id);

    int updateByGrssCourse(GrssCourse grssCourse);
    
    List<String> selectCourseIdsByCoachId(String coachId);

	GrssCourse selectByCoachId(String coachId);
}