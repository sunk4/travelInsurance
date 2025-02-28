package com.roman.Insurance.mainCustomer;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MainCustomerMapper {
    MainCustomerEntity toEntity (MainCustomerDto mainCustomerDTO);

    MainCustomerDto toDto (MainCustomerEntity customerEntity);

    List<MainCustomerDto> entityListToDto (List<MainCustomerEntity> customerEntities);
}
