package com.roman.Insurance.encryption;

import com.roman.Insurance.mainCustomer.MainCustomerEntity;
import com.roman.Insurance.insuredPerson.InsuredPersonEntity;

public interface EncryptionService {
    MainCustomerEntity encrypt (MainCustomerEntity customerEntity) throws Exception;

    MainCustomerEntity decrypt (MainCustomerEntity customerEntity) throws Exception;
    InsuredPersonEntity encrypt (InsuredPersonEntity insuredPersonEntity) throws Exception;
    InsuredPersonEntity decrypt (InsuredPersonEntity insuredPersonEntity) throws Exception;
}
