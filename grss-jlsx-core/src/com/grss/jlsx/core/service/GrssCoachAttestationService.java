package com.grss.jlsx.core.service;

import com.grss.jlsx.core.bean.GrssCoachAttestation;

public interface GrssCoachAttestationService {
    int deleteGcaById(String id);

    int addByGrssCoachAttestation(GrssCoachAttestation grssCoachAttestation);

    GrssCoachAttestation findGcaById(String id);

    int updateByGrssCoachAttestation(GrssCoachAttestation grssCoachAttestation);
    
    GrssCoachAttestation findGcaByCoachId(String coachId);
}