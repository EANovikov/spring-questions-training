package com.xevgnov.proxy.bean.post.processor.use.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.xevgnov.proxy.bean.post.processor.use.dto.Temperature;
import com.xevgnov.proxy.bean.post.processor.use.dto.TemperatureMode;

public class TemperatureServiceToCelsiusProxy implements TemperatureService {

  private TemperatureService temperatureService;

  //TemperatureServiceToCelsiusProxy does not only implements TemperatureService interface
  // It also injects and uses existing fahrenheitTemperatureService logic
  public TemperatureServiceToCelsiusProxy(TemperatureService temperatureService) {
    this.temperatureService = temperatureService;
  }

  // gets a temperature in celsius
  public Temperature getCurrentTemperatue() {
    Temperature farenhateValue = temperatureService.getCurrentTemperatue();
    // proxy behaviour is added here: convert fahrenheit to celsius
    double celsiusTemperature = (farenhateValue.getValue() - 32.0) / 1.8;
    return new Temperature(celsiusTemperature, TemperatureMode.CELSIUS);
  }

}
