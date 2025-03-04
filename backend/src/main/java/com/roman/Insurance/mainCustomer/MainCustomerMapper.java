package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.mainCustomer.request.MainCustomerRequest;
import com.roman.Insurance.mainCustomer.response.MainCustomerResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MainCustomerMapper {
    MainCustomerEntity toEntity (MainCustomerRequest mainCustomerRequest);

    MainCustomerResponse toDto (MainCustomerEntity customerEntity);

    List<MainCustomerResponse> entityListToDto (List<MainCustomerEntity> customerEntities);
}
