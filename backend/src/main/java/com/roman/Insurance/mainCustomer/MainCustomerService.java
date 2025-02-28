package com.roman.Insurance.mainCustomer;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface MainCustomerService {
    UUID createMainCustomer (MainCustomerDto mainCustomerDTO) throws Exception;

    MainCustomerEntity getCustomerByIdEncrypted (UUID customerId) throws Exception;
    MainCustomerEntity getCustomerById(UUID customerId);
}
