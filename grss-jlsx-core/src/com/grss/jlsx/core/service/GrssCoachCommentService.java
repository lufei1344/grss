package com.grss.jlsx.core.service;

import java.util.List;

import com.grss.jlsx.core.bean.GrssCoachComment;

public interface GrssCoachCommentService {
    int deleteById(Integer id);

    int addCoachComment(GrssCoachComment coachComment);

    GrssCoachComment findById(Integer id);

    int updateCoachComment(GrssCoachComment coachComment);

	List<GrssCoachComment> findCoachCommentList(String coachId);

}