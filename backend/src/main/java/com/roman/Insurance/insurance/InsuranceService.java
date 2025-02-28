package com.roman.Insurance.insurance;

import java.util.UUID;

public interface InsuranceService {

    UUID createInsurance (InsuranceDTO insuranceDTO, UUID mainCustomerId,
                          double totalPrice) throws Exception;
    InsuranceEntity getInsuranceEntityById (UUID id);
    void updateUrlPreview (UUID id,String url);
    void updateStatusOfPaymentAndUrlPayed (UUID id, String urlPdfPayed);
}
