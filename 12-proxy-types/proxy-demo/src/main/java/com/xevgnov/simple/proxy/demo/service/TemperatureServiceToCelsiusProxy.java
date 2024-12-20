package com.xevgnov.simple.proxy.demo.service;

import java.util.concurrent.ThreadLocalRandom;

import com.xevgnov.simple.proxy.demo.service.TemperatureService;

@Service
public class TemperatureServiceToCelsiusProxy implements TemperatureService {

      private TemperatureService temperatureService;     

      public TemperatureServiceToCelsiusProxy(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    // gets a temperature in celsius
      public double getCurrentTemperatue(){
        double farenhateValue = temperatureService.getCurrentTemperatue();
        return (farenhateValue - 32.0) / 1.8;
      }

}
