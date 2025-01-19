package com.xevgnov.spring.di.service;

import java.time.LocalDate;

public class DateServiceImpl implements DateService {
    
      public void printDate(){
        System.out.println(LocalDate.now());
      }

}
