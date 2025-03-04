package com.roman.Insurance.insurance;

import com.roman.Insurance.insurance.request.InsuranceRequest;

import java.util.UUID;

public interface InsuranceService {

    UUID createInsurance (
            InsuranceRequest insuranceRequest, UUID mainCustomerId,
            double totalPrice);
    InsuranceEntity getInsuranceEntityById (UUID id);
    void updateUrlPreview (UUID id,String url);
    void updateStatusOfPaymentAndUrlPayed (UUID id, String urlPdfPayed);
}
