package com.xevgnov.iocdi.service;

import com.xevgnov.iocdi.service.TemperatureService;
import org.springframework.stereotype.Service;

@Service
public class CelsiusTemperatureService implements TemperatureService {

    @Override
    public double convert(double temperature) {
        return temperature * 1.8 + 32.0;
    }

    @Override
    public void print(double temperature) {
        System.out.println(temperature + " °C");     
    }
    
}
