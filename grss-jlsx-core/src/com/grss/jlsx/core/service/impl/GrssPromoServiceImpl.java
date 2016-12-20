package com.grss.jlsx.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssPromo;
import com.grss.jlsx.core.dao.GrssPromoDao;
import com.grss.jlsx.core.service.GrssPromoService;

@Service
public class GrssPromoServiceImpl extends BaseService  implements GrssPromoService{
	@Resource
	private GrssPromoDao grssPromoDao;
	@Override
	public int deletePromoById(String id) {
		return grssPromoDao.deleteById(id);
	}

	@Override
	public int addByGrssPromo(GrssPromo grssPromo) {
		return grssPromoDao.insertByGrssPromo(grssPromo);
	}

	@Override
	public GrssPromo findPromoById(String id) {
		return grssPromoDao.selectById(id);
	}

	@Override
	public int updateByGrssPromo(GrssPromo grssPromo) {
		return grssPromoDao.updateByGrssPromo(grssPromo);
	}

	@Override
	public List<GrssPromo> findPromosByUserId(String userId) {
		Map<String,Object> params=getPageParam();
		params.put("userId", userId);
		return grssPromoDao.selectByUserId(params);
	}

	@Override
	public GrssPromo findPromoByCode(String code) {
		return grssPromoDao.selectByCode(code);
	}
}
