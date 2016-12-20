package com.grss.jlsx.core.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssAssets;
import com.grss.jlsx.core.bean.GrssDrawMoney;
import com.grss.jlsx.core.bean.GrssDrawMoneyVO;
import com.grss.jlsx.core.dao.GrssAssetsDao;
import com.grss.jlsx.core.dao.GrssDrawMoneyDao;
import com.grss.jlsx.core.pages.Pager;
import com.grss.jlsx.core.service.GrssDrawMoneyService;

@Service
public class GrssDrawMoneyServiceImpl extends BaseService implements GrssDrawMoneyService {
	@Resource
	private GrssDrawMoneyDao grssDrawMoneyDao;
	
	@Resource
	private GrssAssetsDao grssAssetsDao;
	
	@Override
	public int deleteById(String id) {
		return grssDrawMoneyDao.deleteById(id);
	}

	@Override
	public int addByGrssDrawMoney(GrssDrawMoney grssDrawMoney) {
		return grssDrawMoneyDao.insertByGrssDrawMoney(grssDrawMoney);
	}

	@Override
	public GrssDrawMoney findById(String id) {
		return grssDrawMoneyDao.selectById(id);
	}

	@Override
	public int updateByGrssDrawMoney(GrssDrawMoney grssDrawMoney) {
		return grssDrawMoneyDao.updateByGrssDrawMoney(grssDrawMoney);
	}

	@Override
	public Pager<GrssDrawMoneyVO> findDrawMoneyList(Map<String, Object> params) {
		List<GrssDrawMoneyVO> datas=grssDrawMoneyDao.selectDrawMoneyList(getPageParam(params));
		Integer total=grssDrawMoneyDao.selectDrawMoneyListCount(params);
		Pager<GrssDrawMoneyVO> pager=new Pager<GrssDrawMoneyVO>(total, datas);
		return pager;
	}

	@Override
	public List<GrssDrawMoneyVO> findDrawMoneyData(Map<String, Object> params) {
		return grssDrawMoneyDao.selectDrawMoneyData(params);
	}

	@Override
	public void addByGrssDrawMoney(GrssDrawMoney grssDrawMoney, GrssAssets grssAssets) {
		this.addByGrssDrawMoney(grssDrawMoney);
		grssAssets.setAmount((Float.parseFloat(grssAssets.getAmount()) - grssDrawMoney.getAmount().floatValue()) + ""); 
		grssAssetsDao.updateByGrssAssets(grssAssets);
	}

}
