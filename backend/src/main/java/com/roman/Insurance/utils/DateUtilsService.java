package com.roman.Insurance.utils;

import java.time.LocalDate;

public interface DateUtilsService {
    long calculateDateDifferenceInDays (
            LocalDate startDate,
            LocalDate endDate
    );

}
