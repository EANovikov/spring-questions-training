package com.xevgnov.simple.proxy.demo.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.xevgnov.simple.proxy.demo.dto.Temperature;
import com.xevgnov.simple.proxy.demo.dto.TemperatureMode;

@Primary
@Service("fahrenheitTemperatureService")
public class TemperatureServiceImpl implements TemperatureService {

  // gets a temperature in Fahrenheits
  public Temperature getCurrentTemperatue() {
    double currentTemperature = ThreadLocalRandom.current().nextDouble(30, 65);
    return new Temperature(currentTemperature, TemperatureMode.FARENHEIT);
  }

}
