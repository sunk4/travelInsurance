package com.roman.Insurance.insurance.request;

import com.roman.Insurance.enums.StatusOfPayment;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record InsuranceRequest(
        UUID id,
        UUID countryId,

        @NotNull
        @FutureOrPresent(message = "Start date must be today or in the future.")
        LocalDate startDate,

        @NotNull
        @FutureOrPresent(message = "End date must be today or in the future.")
        LocalDate endDate,

        List<UUID> insuranceTypeIds,

        StatusOfPayment statusOfPayment,

        String urlInsurancePreview,
        String urlInsurancePayed,
        double totalPrice,

        UUID customerId

) {
}
