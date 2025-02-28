package com.roman.Insurance.utils;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface DateUtilsService {
    long calculateDateDifferenceInDays (
            LocalDate startDate,
            LocalDate endDate
    );

}
