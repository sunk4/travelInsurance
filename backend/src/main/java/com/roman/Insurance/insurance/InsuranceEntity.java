package com.roman.Insurance.insurance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.roman.Insurance.country.CountryEntity;
import com.roman.Insurance.mainCustomer.MainCustomerEntity;
import com.roman.Insurance.enums.StatusOfPayment;
import com.roman.Insurance.insuranceType.InsuranceTypeEntity;
import com.roman.Insurance.insuredPerson.InsuredPersonEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
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
import java.util.List;
import java.util.UUID;

@Entity
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "insurances")
public class InsuranceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    @NotNull(message = "Country is required")
    private CountryEntity country;
    @NotNull
    @FutureOrPresent(message = "Start date must be today or in the future.")
    private LocalDate startDate;
    @NotNull
    @FutureOrPresent(message = "End date must be today or in the future.")
    private LocalDate endDate;

    @ManyToMany
    @JoinTable(
            name = "insurance_insurance_type",
            joinColumns = @JoinColumn(name = "insurance_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_type_id")
    )
    private List<InsuranceTypeEntity> insuranceTypes;

    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusOfPayment statusOfPayment;

    @NotNull
    private double totalPrice;

    private String urlInsurancePreview;
    private String urlInsurancePayed;

    @ManyToOne
    @JoinColumn(name = "main_customer_id")
    @JsonBackReference
    private MainCustomerEntity customer;

    @OneToMany(mappedBy = "insurance")
    @JsonManagedReference
    private List<InsuredPersonEntity> insuredPersons;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
