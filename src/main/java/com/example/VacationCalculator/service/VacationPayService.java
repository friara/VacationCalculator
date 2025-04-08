package com.example.VacationCalculator.service;

import com.example.VacationCalculator.dto.VacationPayRequest;
import com.example.VacationCalculator.dto.VacationPayResponse;

public interface VacationPayService {
    public VacationPayResponse calculateVacationPay(VacationPayRequest vacationPayRequest);
}
