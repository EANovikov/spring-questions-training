package com.xevgnov.iocdi.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Temperature {
    private double value;
    private TemperatureMode mode;
}
