package com.roman.Insurance.insurance;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity,
        UUID> {
}
