package com.roman.Insurance.mainCustomer;

import com.roman.Insurance.mainCustomer.request.MainCustomerRequest;
import com.roman.Insurance.mainCustomer.response.MainCustomerResponse;

import java.util.UUID;
import java.util.List;

public interface MainCustomerService {
    UUID createMainCustomer (MainCustomerRequest mainCustomerRequest) throws Exception;

    MainCustomerEntity getCustomerByIdEncrypted (UUID customerId) throws Exception;
    MainCustomerEntity getCustomerById(UUID customerId);

    List<MainCustomerResponse> getAllCustomers ();
}
