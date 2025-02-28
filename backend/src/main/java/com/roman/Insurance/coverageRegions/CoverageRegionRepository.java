package com.roman.Insurance.coverageRegions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CoverageRegionRepository extends JpaRepository<CoverageRegionEntity, UUID> {
}
