package com.example.VacationCalculator.controller;

import com.example.VacationCalculator.dto.VacationPayRequest;
import com.example.VacationCalculator.dto.VacationPayResponse;
import com.example.VacationCalculator.service.VacationPayService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;


@RestController
@RequestMapping("/calculate")
public class VacationPayController {

    private final VacationPayService vacationPayService;

    public VacationPayController(VacationPayService vacationPayService) {
        this.vacationPayService = vacationPayService;
    }

    @GetMapping
    public VacationPayResponse calculateVacationPay(
            @RequestParam double salaryAverage,
            @RequestParam int vacationDaysAmount,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate vacationStartDate)
    {
        VacationPayRequest vacationPayRequest = new VacationPayRequest();
        vacationPayRequest.setSalaryAverage(salaryAverage);
        vacationPayRequest.setVacationDaysAmount(vacationDaysAmount);
        vacationPayRequest.setVacationStartDate(vacationStartDate);

        return vacationPayService.calculateVacationPay(vacationPayRequest);
    }
}
