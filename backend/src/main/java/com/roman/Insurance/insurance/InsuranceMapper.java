package com.roman.Insurance.insurance;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    InsuranceEntity toEntity (InsuranceDTO insuranceDTO);

    InsuranceDTO toDto (InsuranceEntity insuranceEntity);

    List<InsuranceDTO> entityListToDto (List<InsuranceEntity> insuranceEntities);

}
