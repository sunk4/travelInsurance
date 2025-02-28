package com.roman.Insurance.riskFactor;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RiskFactorMapper {
    RiskFactorDto toDto(RiskFactorEntity riskFactorEntity);
    List<RiskFactorDto> entityListToDto(List<RiskFactorEntity> riskFactorEntities);
}
