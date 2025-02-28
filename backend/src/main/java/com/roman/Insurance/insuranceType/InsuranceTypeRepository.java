package com.roman.Insurance.insuranceType;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuranceTypeRepository extends JpaRepository<InsuranceTypeEntity, UUID> {
}
