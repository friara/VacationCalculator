package com.example.VacationCalculator.service;

import java.time.LocalDate;

public interface HolidayService {
    public int getWorkingDaysCount(LocalDate startDate, LocalDate endDate);
}
