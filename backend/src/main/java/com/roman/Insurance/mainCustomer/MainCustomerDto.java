package com.roman.Insurance.mainCustomer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

public record MainCustomerDto(
        UUID id,

        @NotBlank(message = "First name is required")
        String firstName,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Phone number is required")
        String phoneNumber,

        @NotBlank(message = "Address is required")
        String address,

        @NotBlank(message = "City is required")
        String city,

        @NotBlank(message = "State is required")
        String state,

        @NotBlank(message = "Zip code is required")
        String zipCode,

        @NotBlank(message = "Personal identification number is required")
        String personalIdentificationNumber,


        LocalDateTime createdAt,

        LocalDateTime updatedAt
) {
}
