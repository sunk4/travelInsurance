package com.roman.Insurance.riskFactor;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RiskFactorRepository  extends JpaRepository<RiskFactorEntity, UUID> {
}
