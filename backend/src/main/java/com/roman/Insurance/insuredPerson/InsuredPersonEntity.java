package com.roman.Insurance.insuredPerson;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.roman.Insurance.ageCategories.AgeCategoryEntity;
import com.roman.Insurance.insurance.InsuranceEntity;
import com.roman.Insurance.riskFactor.RiskFactorEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "insured_person")
public class InsuredPersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String encryptedFistName;

    private String encryptedLastName;

    private String encryptedDateOfBirth;

    @Transient
    @NotBlank(message = "First name is required")
    private String firstName;
    @Transient
    @NotBlank(message = "Last name is required")
    private String lastName;
    @Transient
    @NotNull(message = "Date of birth is required")
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "insurance_id", nullable = false)
    @NotNull(message = "Insurance is required")
    @JsonBackReference
    private InsuranceEntity insurance;

    @ManyToOne
    @JoinColumn(name = "age_category_id", nullable = false)
    @NotNull(message = "Age category is required")

    private AgeCategoryEntity ageCategory;

    @ManyToOne
    @JoinColumn(name = "risk_factor_id", nullable = false)
    @NotNull(message = "Risk factor is required")

    private RiskFactorEntity riskFactor;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

}
