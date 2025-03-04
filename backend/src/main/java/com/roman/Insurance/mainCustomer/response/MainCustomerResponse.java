package com.roman.Insurance.mainCustomer.response;

import com.roman.Insurance.insurance.response.InsuranceResponse;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record MainCustomerResponse(
        UUID id,

        String firstName,

        String lastName,

        String email,

        String phoneNumber,

        String address,

        String city,

        String state,

        String zipCode,

        String personalIdentificationNumber,
        List<InsuranceResponse> insurances,

        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
