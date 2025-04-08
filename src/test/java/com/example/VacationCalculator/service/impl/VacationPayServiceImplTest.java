package com.example.VacationCalculator.service.impl;

import com.example.VacationCalculator.dto.VacationPayRequest;
import com.example.VacationCalculator.dto.VacationPayResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VacationPayServiceImplTest {

    @Mock
    private HolidayServiceImpl holidayService;

    @InjectMocks
    private VacationPayServiceImpl vacationPayService;

    @Test
    void calculateVacationPay_WithoutStartDate() {
        VacationPayRequest request = new VacationPayRequest();
        request.setSalaryAverage(293.0);
        request.setVacationDaysAmount(20);

        VacationPayResponse response = vacationPayService.calculateVacationPay(request);

        assertEquals(293.0 / 29.3 * 20, response.getPayAmount());
    }

    @Test
    void calculateVacationPay_WithStartDate() {
        VacationPayRequest request = new VacationPayRequest();
        request.setSalaryAverage(293.0);
        request.setVacationDaysAmount(20);
        LocalDate startDate = LocalDate.of(2024, 3, 1);
        request.setVacationStartDate(startDate);

        when(holidayService.getWorkingDaysCount(startDate, startDate.plusDays(request.getVacationDaysAmount() - 1)))
                .thenReturn(13);

        VacationPayResponse response = vacationPayService.calculateVacationPay(request);

        assertEquals(293.0 / 29.3 * 13, response.getPayAmount());
    }

}