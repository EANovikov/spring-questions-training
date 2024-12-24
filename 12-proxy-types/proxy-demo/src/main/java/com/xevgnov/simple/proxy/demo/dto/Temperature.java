package com.xevgnov.simple.proxy.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Temperature {
    private double value;
    private TemperatureMode mode;
}
