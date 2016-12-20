package com.grss.jlsx.core.dao;

import java.util.List;
import java.util.Map;

import com.grss.jlsx.core.bean.GrssClub;

public interface GrssClubDao {
	
	int deleteById(String clubId);

    int insertByGrssClub(GrssClub grssClub);
    
    int updateByGrssClub(GrssClub grssClub);
    
    GrssClub selectById(String clubId);
    
    GrssClub selectByIds(Map<String,Object> params);
    
    GrssClub findGrssClub(GrssClub grssClub);

	public int countAllNum(Map<String, Object> map);
	
	public List<GrssClub> findAll(Map<String, Object> map);

	List<GrssClub> selectCollectClubs(Map<String, Object> map);

	List<GrssClub> selectGrssClubAllList(Map<String, Object> pageParam);

	int selectGrssClubAllCount(Map<String, Object> params);


}
