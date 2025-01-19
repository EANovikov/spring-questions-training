package com.xevgnov.spring.di.service;

import java.time.LocalTime;

public class TimeServiceImpl implements TimeService {
  public void printTime() {
    System.out.println(LocalTime.now());
  }

}
