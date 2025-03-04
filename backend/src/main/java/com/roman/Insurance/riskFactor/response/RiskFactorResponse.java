package com.roman.Insurance.riskFactor.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record RiskFactorResponse(
    UUID id,
    String name,
    double riskFactor,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
