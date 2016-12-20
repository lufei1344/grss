package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssFeedback;

public interface GrssFeedbackDao {
    int deleteById(String id);

    int insertByGrssFeedback(GrssFeedback grssFeedback);

    GrssFeedback selectById(String id);

    int updateByGrssFeedback(GrssFeedback grssFeedback);

}