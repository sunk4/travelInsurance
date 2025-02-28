package com.roman.Insurance.mainCustomer;

import java.util.UUID;

public interface MainCustomerService {
    UUID createMainCustomer (MainCustomerDto mainCustomerDTO) throws Exception;

    MainCustomerEntity getCustomerById (UUID customerId) throws Exception;
}
