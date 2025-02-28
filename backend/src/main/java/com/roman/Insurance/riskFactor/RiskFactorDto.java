package com.roman.Insurance.riskFactor;

import java.time.LocalDateTime;
import java.util.UUID;

public record RiskFactorDto(
    UUID id,
    String name,
    double riskFactor,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
