package com.roman.Insurance.insuranceType;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record InsuranceTypeCalculationDto(LocalDate startDate,
                                          LocalDate endDate,
                                          List<UUID> ageCategoryIds) {

}
