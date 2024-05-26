package com.xevgnov.stereotypes.domain;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CounterDto {

    private String info;
    private int count;    
}