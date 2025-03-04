package com.roman.Insurance.coverageRegions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.roman.Insurance.country.CountryEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
@Table(name = "coverage_regions")
public class CoverageRegionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotBlank(message = "Coverage region name is required")
    private String name;
    @NotBlank(message = "Description is required")
    private String description;

    @OneToMany(mappedBy = "coverageRegion", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CountryEntity> countries;

    @NotNull(message = "Base price per day is required")
    @Min(value = 1, message = "Base price per day must be at least 1.")
    private Double basePricePerDay;
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;
}
