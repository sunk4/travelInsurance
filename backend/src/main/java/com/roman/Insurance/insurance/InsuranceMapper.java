package com.roman.Insurance.insurance;

import com.roman.Insurance.insurance.request.InsuranceRequest;
import com.roman.Insurance.insurance.response.InsuranceResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InsuranceMapper {

    InsuranceEntity toEntity (InsuranceRequest insuranceRequest);

    InsuranceResponse toDto (InsuranceEntity insuranceEntity);

    List<InsuranceRequest> entityListToDto (List<InsuranceEntity> insuranceEntities);

}
