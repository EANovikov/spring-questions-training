package com.xevgnov.concurrency.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Service
public class RateCalculatorServiceImpl implements RateCalculatorService {

    private List<Integer> rates = new ArrayList<>();

    @Override
    public void addToRate(int mark) {
        rates.add(mark);
        log.info("added rate {}", mark);
        log.info("current marks {}", rates);
    }

    @Override
    public double getRate() {
        Integer sum = rates.stream().reduce(0, Integer::sum);
        log.info("summ of marks {}", sum);
        double middleRate = (double)sum/rates.size();
        log.info("middle rate {}", middleRate);
        rates.clear();
        return middleRate;
    }



}
