package com.roman.Insurance.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DateUtilsServiceImpl implements DateUtilsService {
    @Override
    public long calculateDateDifferenceInDays (
            LocalDate startDate,
            LocalDate endDate
    ) {
        if (startDate == null || endDate == null) return 0;
        if (startDate.equals(endDate)) return 1;
        return startDate.datesUntil(endDate).count() + 1;
    }
}
