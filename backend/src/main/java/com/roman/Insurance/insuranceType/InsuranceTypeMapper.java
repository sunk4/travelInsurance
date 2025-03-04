package com.roman.Insurance.insuranceType;

import com.roman.Insurance.insuranceType.response.InsuranceTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsuranceTypeMapper {

    InsuranceTypeResponse toDto (InsuranceTypeEntity entity);

    List<InsuranceTypeResponse> entityListToDto (List<InsuranceTypeEntity> entities);
}
