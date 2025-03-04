package com.roman.Insurance.insurance.response;

import com.roman.Insurance.enums.StatusOfPayment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record InsuranceResponse(
        UUID id,
        UUID countryId,

        LocalDate startDate,

        LocalDate endDate,

        List<UUID> insuranceTypeIds,

        StatusOfPayment statusOfPayment,

        String urlInsurancePreview,
        String urlInsurancePayed,
        double totalPrice,

        UUID customerId,

        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
