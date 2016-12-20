package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssCoachComment;

public interface GrssCoachCommentDao {
    int deleteById(Integer id);

    int insertCoachComment(GrssCoachComment coachComment);

    GrssCoachComment selectById(Integer id);

    int updateCoachComment(GrssCoachComment coachComment);

	List<GrssCoachComment> selectCommentList(Map<String, Object> params);

}