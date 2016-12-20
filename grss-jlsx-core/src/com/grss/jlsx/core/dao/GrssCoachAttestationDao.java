package com.grss.jlsx.core.dao;

import com.grss.jlsx.core.bean.GrssCoachAttestation;

public interface GrssCoachAttestationDao {
    int deleteById(String id);

    int insertByGrssCoachAttestation(GrssCoachAttestation grssCoachAttestation);

    GrssCoachAttestation selectById(String id);

    int updateByGrssCoachAttestation(GrssCoachAttestation grssCoachAttestation);
    
    GrssCoachAttestation selectByCoachId(String coachId);
}