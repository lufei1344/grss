package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssFeedback;

public interface GrssFeedbackService {
    int deleteById(String id);

    int addByGrssFeedback(GrssFeedback grssFeedback);

    GrssFeedback findById(String id);

    int updateByGrssFeedback(GrssFeedback grssFeedback);

}