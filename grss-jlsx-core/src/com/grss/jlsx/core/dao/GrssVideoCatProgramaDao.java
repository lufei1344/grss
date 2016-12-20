package com.grss.jlsx.core.dao;

import java.util.List;

import com.grss.jlsx.core.bean.GrssVideoCatPrograma;

public interface GrssVideoCatProgramaDao {
    int deleteById(Integer id);

    int insertByGrssVideoCatPrograma(GrssVideoCatPrograma grssVideoCatPrograma);

    GrssVideoCatPrograma selectById(Integer id);

    int updateByGrssVideoCatPrograma(GrssVideoCatPrograma grssVideoCatPrograma);

	List<GrssVideoCatPrograma> selectGrssVideoCatProgramaAll();

}