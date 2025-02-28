package com.roman.Insurance.mainCustomer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.roman.Insurance.insurance.InsuranceEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "main_customers")
public class MainCustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String encryptedFirstName;

    private String encryptedLastName;

    private String encryptedEmail;

    private String encryptedPhoneNumber;

    private String encryptedAddress;

    private String encryptedCity;

    private String encryptedState;

    private String encryptedZipCode;

    private String encryptedPersonalIdentificationNumber;

    @Transient
    @NotBlank(message = "First name is required")
    private String firstName;
    @Transient
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Transient
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @Transient
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @Transient
    @NotBlank(message = "Address is required")
    private String address;
    @Transient
    @NotBlank(message = "City is required")
    private String city;
    @Transient
    @NotBlank(message = "State is required")
    private String state;
    @Transient
    @NotBlank(message = "Zip code is required")
    private String zipCode;
    @Transient
    @NotBlank(message = "Personal identification number is required")
    private String personalIdentificationNumber;

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<InsuranceEntity> insurances;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

}
