package com.roman.Insurance.country.request;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record PriceCalculationRequest(

        UUID countryId,

        List<UUID> ageCategoriesIds,
        LocalDate startDate,
        LocalDate endDate,
        List<UUID> riskFactorIds

) {

}
