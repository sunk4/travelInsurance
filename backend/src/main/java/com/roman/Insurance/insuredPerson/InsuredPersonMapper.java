package com.roman.Insurance.insuredPerson;

import com.roman.Insurance.insuredPerson.request.InsuredPersonRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsuredPersonMapper {
    InsuredPersonEntity toEntity (InsuredPersonRequest insuredPersonRequest);
}
