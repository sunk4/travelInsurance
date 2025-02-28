package com.roman.Insurance.insuranceType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roman.Insurance.insurance.InsuranceEntity;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "insurance_types")
public class InsuranceTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Base price per day is required")
    private double basePricePerDay;

    @NotNull(message = "Is additional insurance is required")
    private Boolean isAdditionalInsurance;

    @NotNull(message = "Is price total is required")
    private Boolean isPriceTotal;

    @ManyToMany(mappedBy = "insuranceTypes")
    @JsonIgnore
    private List<InsuranceEntity> insurances;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
