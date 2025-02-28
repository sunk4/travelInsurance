package com.roman.Insurance.riskFactor;

import java.util.List;
import java.util.UUID;

public interface RiskFactorService {
    List<RiskFactorDto> findAllRiskFactors ();

    RiskFactorDto getRiskFactorById (UUID id);
    RiskFactorEntity getRiskFactorEntityById (UUID id);
}
