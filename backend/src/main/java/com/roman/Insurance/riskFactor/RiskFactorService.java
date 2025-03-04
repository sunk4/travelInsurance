package com.roman.Insurance.riskFactor;

import com.roman.Insurance.riskFactor.response.RiskFactorResponse;

import java.util.List;
import java.util.UUID;

public interface RiskFactorService {
    List<RiskFactorResponse> findAllRiskFactors ();

    RiskFactorResponse getRiskFactorById (UUID id);

    RiskFactorEntity getRiskFactorEntityById (UUID id);
}
