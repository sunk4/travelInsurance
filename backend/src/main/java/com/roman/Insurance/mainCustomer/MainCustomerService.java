package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.common.PageResponse;
import com.roman.Insurance.mainCustomer.request.MainCustomerRequest;
import com.roman.Insurance.mainCustomer.response.MainCustomerResponse;

import java.util.UUID;

public interface MainCustomerService {
    UUID createMainCustomer (MainCustomerRequest mainCustomerRequest) throws Exception;

    MainCustomerEntity getCustomerByIdEncrypted (UUID customerId) throws Exception;
    MainCustomerEntity getCustomerById(UUID customerId);

    PageResponse<MainCustomerResponse> getAllCustomers (UUID countryId, UUID coverageRegionId, String firstName, String lastName, int pageNum, int pageSize) throws Exception;
    MainCustomerResponse getCustomerByIdDto(UUID id) throws Exception;
}
