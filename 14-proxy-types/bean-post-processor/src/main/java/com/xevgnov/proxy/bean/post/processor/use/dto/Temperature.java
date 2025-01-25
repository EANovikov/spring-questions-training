package com.xevgnov.proxy.bean.post.processor.use.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Temperature {
    private double value;
    private TemperatureMode mode;
}
