package com.xevgnov.concurrency.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
// Uncomment to fix race condition
//@Scope(value = BeanDefinition.SCOPE_PROTOTYPE)
@Service
public class RateCalculatorServiceImpl implements RateCalculatorService {

    private final List<Integer> rates = new ArrayList<>();

    @Override
    public void addToRate(int mark) {
        rates.add(mark);
        log.info("Added rate {}", mark);
        log.info("Current marks {}", rates);
    }

    @Override
    public double getRate() {
        Integer sum = rates.stream().reduce(0, Integer::sum);
        log.info("Summ of marks {}", sum);
        double avereageRate = (double) sum / rates.size();
        log.info("Average rate {}", avereageRate);
        rates.clear();
        return avereageRate;
    }

}
