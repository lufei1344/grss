package com.grss.jlsx.core.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssVideoCatPrograma;
import com.grss.jlsx.core.dao.GrssVideoCatProgramaDao;
import com.grss.jlsx.core.service.GrssVideoCatProgramaService;

@Service
public class GrssVideoCatProgramaServiceImpl implements GrssVideoCatProgramaService {
	
	@Resource
	private GrssVideoCatProgramaDao grssVideoCatProgramaDao;
	
	@Override
	public List<GrssVideoCatPrograma> findGrssVideoCatProgramaAll() {
		return grssVideoCatProgramaDao.selectGrssVideoCatProgramaAll();
	}

}
