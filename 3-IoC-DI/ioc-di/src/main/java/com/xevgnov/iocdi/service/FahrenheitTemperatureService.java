package com.xevgnov.iocdi.service;

import com.xevgnov.iocdi.service.TemperatureService;
import org.springframework.stereotype.Service;

@Service
public class FahrenheitTemperatureService implements TemperatureService {
    
    @Override
    public long convert(double temperature) {
        return (temperature - 32.0) / 1.8;
    }

    @Override
    public void print(double temperature) {
        System.out.println(temperature + " °F");        
    }
}
