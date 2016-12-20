package com.grss.jlsx.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssCourse;
import com.grss.jlsx.core.dao.GrssCourseDao;
import com.grss.jlsx.core.dao.GrssOrderDao;
import com.grss.jlsx.core.service.GrssCourseService;
@Service("grssCourseService")
public class GrssCourseServiceImpl implements GrssCourseService {
	@Resource
	private GrssCourseDao grssCourseDao;
	@Resource
	private GrssOrderDao grssOrderDao;
	
	@Override
	public int deleteById(String id) {
		return grssCourseDao.deleteById(id);
	}

	@Override
	public int addByGrssCourse(GrssCourse grssCourse) {
		return grssCourseDao.insertByGrssCourse(grssCourse);
	}

	@Override
	public GrssCourse findById(String id) {
		return grssCourseDao.selectById(id);
	}

	@Override
	public int updateByGrssCourse(GrssCourse grssCourse) {
		return grssCourseDao.updateByGrssCourse(grssCourse);
	}

	@Override
	public List<String> findCourseIdsByCoachId(String coachId) {
		return grssCourseDao.selectCourseIdsByCoachId(coachId);
	}

	@Override
	public Integer findGuidanceCount(String coachId) {
		List<String> courseIds=findCourseIdsByCoachId(coachId);
		if(!courseIds.isEmpty()){
			Integer guidanceCount=grssOrderDao.selectOrderCountByCoach(courseIds);
			return guidanceCount;
		}else{
			return 0;
		}
	}

	@Override
	public GrssCourse findByCoachId(String coachId) {
		return grssCourseDao.selectByCoachId(coachId);
	}

}
