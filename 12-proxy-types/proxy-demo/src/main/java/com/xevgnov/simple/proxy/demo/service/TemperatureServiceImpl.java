package com.xevgnov.simple.proxy.demo.service;

import java.util.concurrent.ThreadLocalRandom;

import com.xevgnov.simple.proxy.demo.service.TemperatureService;

@Service
public class TemperatureServiceImpl implements TemperatureService {
    
      // gets a temperature in Fahrenheits
      public double getCurrentTemperatue(){
        return ThreadLocalRandom.current().nextDouble(30, 65);
      }


}
