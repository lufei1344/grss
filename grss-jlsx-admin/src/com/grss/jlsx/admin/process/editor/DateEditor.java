package com.grss.jlsx.admin.process.editor;

import java.beans.PropertyEditorSupport;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.grss.jlsx.core.utils.DateUtil;


public class DateEditor extends PropertyEditorSupport {
	@Override
	public void setAsText(String text) throws IllegalArgumentException {

		if (!StringUtils.hasText(text)) {
			setValue(null);
		} else {
			setValue(DateUtil.date2Str(new Date()));
		}
	}

	@Override
	public String getAsText() {

		return getValue().toString();
	}
}
