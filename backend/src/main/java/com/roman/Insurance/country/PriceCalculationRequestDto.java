package com.roman.Insurance.country;

import com.roman.Insurance.ageCategories.AgeCategoryDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PriceCalculationRequestDto(

        UUID countryId,

        List<UUID> ageCategoriesIds,
        LocalDate startDate,
        LocalDate endDate,
        List<UUID> riskFactorIds

) {

}
