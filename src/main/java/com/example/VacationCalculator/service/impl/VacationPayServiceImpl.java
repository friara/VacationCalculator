package com.example.VacationCalculator.service.impl;

import com.example.VacationCalculator.dto.VacationPayRequest;
import com.example.VacationCalculator.dto.VacationPayResponse;
import com.example.VacationCalculator.service.HolidayService;
import com.example.VacationCalculator.service.VacationPayService;
import org.springframework.stereotype.Service;

@Service
public class VacationPayServiceImpl implements VacationPayService {
    private static final double AVERAGE_WORKING_DAYS_PER_MONTH = 29.3;
    private final HolidayService holidayService;

    public VacationPayServiceImpl(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @Override
    public VacationPayResponse calculateVacationPay(VacationPayRequest vacationPayRequest) {
        VacationPayResponse vacationPayResponse = new VacationPayResponse();
        int paidDaysForCalculate;
        if(vacationPayRequest.getVacationStartDate() != null) {
            paidDaysForCalculate = holidayService.getWorkingDaysCount(
                    vacationPayRequest.getVacationStartDate(),
                    vacationPayRequest.getVacationStartDate().plusDays(vacationPayRequest.getVacationDaysAmount() - 1));

        }
        else {
            paidDaysForCalculate = vacationPayRequest.getVacationDaysAmount();
        }

        double payAmount = calculatePayment(vacationPayRequest.getSalaryAverage(), paidDaysForCalculate);
        vacationPayResponse.setPayAmount(payAmount);

        return vacationPayResponse;
    }

    private double calculatePayment(double salaryAverage, int paidDays) {
        return salaryAverage / AVERAGE_WORKING_DAYS_PER_MONTH * paidDays;
    }
}
