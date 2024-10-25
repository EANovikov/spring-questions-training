package com.xevgnov.iocdi.service;

import org.springframework.stereotype.Service;

import com.xevgnov.iocdi.domain.Temperature;

@Service
public interface TemperatureService {
    
    void print(double temperature);
    Temperature convert(double temperature);
}
