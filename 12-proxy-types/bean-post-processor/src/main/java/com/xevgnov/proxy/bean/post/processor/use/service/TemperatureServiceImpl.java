package com.xevgnov.proxy.bean.post.processor.use.service;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.xevgnov.proxy.bean.post.processor.use.dto.Temperature;
import com.xevgnov.proxy.bean.post.processor.use.dto.TemperatureMode;

@Service
public class TemperatureServiceImpl implements TemperatureService {

  // gets a temperature in Fahrenheits
  public Temperature getCurrentTemperatue() {
    double currentTemperature = ThreadLocalRandom.current().nextDouble(30, 65);
    return new Temperature(currentTemperature, TemperatureMode.FARENHEIT);
  }

}
