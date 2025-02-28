package com.roman.Insurance.insurance;

import java.util.UUID;

public interface InsuranceService {

    UUID createInsurance (InsuranceDTO insuranceDTO, UUID mainCustomerId,
                          double totalPrice) throws Exception;
    InsuranceEntity getInsuranceEntityById (UUID id);
}
