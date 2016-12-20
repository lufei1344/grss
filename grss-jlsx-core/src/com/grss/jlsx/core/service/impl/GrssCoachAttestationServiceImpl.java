package com.grss.jlsx.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grss.jlsx.core.bean.GrssCoachAttestation;
import com.grss.jlsx.core.dao.GrssCoachAttestationDao;
import com.grss.jlsx.core.service.GrssCoachAttestationService;

@Service
public class GrssCoachAttestationServiceImpl implements GrssCoachAttestationService {
	@Resource
	private GrssCoachAttestationDao grssCoachAttestationDao;
	
	@Override
	public int deleteGcaById(String id) {
		return grssCoachAttestationDao.deleteById(id);
	}

	@Override
	public int addByGrssCoachAttestation(
			GrssCoachAttestation grssCoachAttestation) {
		return grssCoachAttestationDao.insertByGrssCoachAttestation(grssCoachAttestation);
	}

	@Override
	public GrssCoachAttestation findGcaById(String id) {
		return grssCoachAttestationDao.selectById(id);
	}

	@Override
	public int updateByGrssCoachAttestation(
			GrssCoachAttestation grssCoachAttestation) {
		return grssCoachAttestationDao.updateByGrssCoachAttestation(grssCoachAttestation);
	}

	@Override
	public GrssCoachAttestation findGcaByCoachId(String coachId) {
		return grssCoachAttestationDao.selectByCoachId(coachId);
	}

}
