package com.example.VacationCalculator.dto;

import java.time.LocalDate;

public class VacationPayRequest {
    private double salaryAverage;
    private int vacationDaysAmount;
    private LocalDate vacationStartDate;

    public double getSalaryAverage() {
        return salaryAverage;
    }

    public void setSalaryAverage(double salaryAverage) {
        this.salaryAverage = salaryAverage;
    }

    public int getVacationDaysAmount() {
        return vacationDaysAmount;
    }

    public void setVacationDaysAmount(int vacationDaysAmount) {
        this.vacationDaysAmount = vacationDaysAmount;
    }

    public LocalDate getVacationStartDate() {
        return vacationStartDate;
    }

    public void setVacationStartDate(LocalDate vacationStartDate) {
        this.vacationStartDate = vacationStartDate;
    }
}
