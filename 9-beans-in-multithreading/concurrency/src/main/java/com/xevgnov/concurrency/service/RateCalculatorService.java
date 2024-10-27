package com.xevgnov.concurrency.service;

public interface RateCalculatorService {
    void addToRate(int mark);

    double getRate();
}
