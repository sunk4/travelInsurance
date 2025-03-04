package com.roman.Insurance.ageCategories.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record AgeCategoryResponse(
        UUID id,
        String name,
        Integer minAge,
        Integer maxAge,
        double priceFactor,
        LocalDateTime createdAt,
        LocalDateTime updatedAt

) {
}
