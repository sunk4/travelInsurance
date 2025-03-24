package com.roman.Insurance.utils;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateUtilsServiceImplTest {

    private final DateUtilsServiceImpl dateUtilsService = new DateUtilsServiceImpl();

    @Test
    void testCalculateDateDifferenceInDays_withEqualDates () {
        LocalDate today = LocalDate.now();
        assertEquals(1, dateUtilsService.calculateDateDifferenceInDays(today,
                today));
    }

    @Test
    void testCalculateDateDifferenceInDays_withDifferentDates () {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        assertEquals(2, dateUtilsService.calculateDateDifferenceInDays(today,
                tomorrow));
    }

}
