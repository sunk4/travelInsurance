package com.roman.Insurance.riskFactor;

import com.roman.Insurance.riskFactor.response.RiskFactorResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RiskFactorMapper {
    RiskFactorResponse toDto (RiskFactorEntity riskFactorEntity);

    List<RiskFactorResponse> entityListToDto (List<RiskFactorEntity> riskFactorEntities);
}
