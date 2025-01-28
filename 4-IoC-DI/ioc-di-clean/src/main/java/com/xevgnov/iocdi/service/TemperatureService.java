package com.xevgnov.iocdi.service;

import com.xevgnov.iocdi.domain.Temperature;
import org.springframework.stereotype.Service;

@Service
public interface TemperatureService {

    void print(double temperature);

    Temperature convert(double temperature);
}
