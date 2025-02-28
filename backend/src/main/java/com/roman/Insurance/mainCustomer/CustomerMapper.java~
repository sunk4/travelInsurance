package com.roman.Insurance.customer;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerEntity toEntity (CustomerDTO customerDTO);

    CustomerDTO toDto (CustomerEntity customerEntity);

    List<CustomerDTO> entityListToDto (List<CustomerEntity> customerEntities);
}
