package com.roman.Insurance.insuranceType;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsuranceTypeMapper {

    InsuranceTypeDto toDto (InsuranceTypeEntity entity);

    List<InsuranceTypeDto> entityListToDto (List<InsuranceTypeEntity> entities);
}
