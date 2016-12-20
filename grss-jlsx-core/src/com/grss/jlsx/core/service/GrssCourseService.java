package com.grss.jlsx.core.service;

import java.util.List;

import com.grss.jlsx.core.bean.GrssCourse;

public interface GrssCourseService {
    int deleteById(String id);

    int addByGrssCourse(GrssCourse grssCourse);

    GrssCourse findById(String id);

    int updateByGrssCourse(GrssCourse grssCourse);
    
    List<String> findCourseIdsByCoachId(String coachId);
    
    Integer findGuidanceCount(String coachId);
    
    GrssCourse findByCoachId(String coachId);
}