package com.example.VacationCalculator.service.impl;

import com.example.VacationCalculator.service.HolidayService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.IntStream;

@Service
public class HolidayServiceImpl implements HolidayService {

    private final RestTemplate restTemplate;

    public HolidayServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public int getWorkingDaysCount(LocalDate startDate, LocalDate endDate) {
        String date1 = startDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String date2 = endDate.format(DateTimeFormatter.BASIC_ISO_DATE);
        String url = String.format("https://isdayoff.ru/api/getdata?date1=%s&date2=%s", date1, date2);
        String response = restTemplate.getForObject(url, String.class);
        if (response == null) {
            throw new IllegalStateException("API isdayoff.ru не отвечает");
        }
        return (int) IntStream.range(0, response.length())
                .filter(i -> response.charAt(i) == '0') // Только рабочие дни
                .count();
    }
}
