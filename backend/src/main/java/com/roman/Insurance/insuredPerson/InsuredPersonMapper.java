package com.roman.Insurance.insuredPerson;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InsuredPersonMapper {
    InsuredPersonEntity toEntity(InsuredPersonDTO insuredPersonDTO);
}
