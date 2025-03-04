package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.mainCustomer.request.MainCustomerRequest;

import java.util.UUID;

public interface MainCustomerService {
    UUID createMainCustomer (MainCustomerRequest mainCustomerRequest) throws Exception;

    MainCustomerEntity getCustomerByIdEncrypted (UUID customerId) throws Exception;
    MainCustomerEntity getCustomerById(UUID customerId);
}
