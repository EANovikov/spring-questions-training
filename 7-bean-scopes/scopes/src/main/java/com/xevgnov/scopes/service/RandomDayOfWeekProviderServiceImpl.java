package com.xevgnov.scopes.service;

import java.time.DayOfWeek;
import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class RandomDayOfWeekProviderServiceImpl implements RandomDayOfWeekProviderService {

    @Override
    public String getDay() {
        return DayOfWeek
        .values()[getRandomDayIndex()]
        .name();
    }

    private int getRandomDayIndex(){
        return new Random().ints(0, 7)
        .findFirst()
        .getAsInt();
    }
    
}
