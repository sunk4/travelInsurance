package com.roman.Insurance.insurance;

import com.roman.Insurance.enums.StatusOfPayment;
import com.roman.Insurance.insuranceType.InsuranceTypeDto;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record InsuranceDTO(
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

        UUID customerId,

        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
