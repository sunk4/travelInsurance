package com.roman.Insurance.riskFactor;

import com.roman.Insurance.riskFactor.response.RiskFactorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RiskFactorServiceImpl implements RiskFactorService {
    private final RiskFactorRepository riskFactorRepository;
    private final RiskFactorMapper riskFactorMapper;

    @Override
    public List<RiskFactorResponse> findAllRiskFactors () {

        List<RiskFactorEntity> riskFactorEntities = riskFactorRepository.findAll();
        return riskFactorMapper.entityListToDto(riskFactorEntities);

    }

    @Override
    public RiskFactorResponse getRiskFactorById (UUID id) {
        RiskFactorEntity riskFactor = riskFactorRepository.findById(id).orElseThrow(() -> new RuntimeException("Risk factor not found"));

        return riskFactorMapper.toDto(riskFactor);
    }

    @Override
    public RiskFactorEntity getRiskFactorEntityById (UUID id) {
        return riskFactorRepository.findById(id).orElseThrow(() -> new RuntimeException("Risk factor not found"));
    }
}
