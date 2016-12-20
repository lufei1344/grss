package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.grss.jlsx.core.bean.GrssVideoCategory;

public interface GrssVideoCategoryDao {
    int deleteById(String id);


    int insertByGrssVideoCategory(GrssVideoCategory grssVideoCategory);

    GrssVideoCategory selectById(@Param("id")String id);

    int updateByGrssVideoCategory(GrssVideoCategory grssVideoCategory);


	List<GrssVideoCategory> selectParentCategoryList(@Param("programaId") Integer programaId);


	List<GrssVideoCategory> selectCategoryByParentIdList(@Param("parentId") String parentId);


	List<GrssVideoCategory> selectCatsList(Map<String, Object> map);


	Integer selectCatsListCount();

	List<GrssVideoCategory> selectParentCats();

	String selectMaxCateCodeByPid(String parentId);


	List<GrssVideoCategory> selectAllVideoCats();

}