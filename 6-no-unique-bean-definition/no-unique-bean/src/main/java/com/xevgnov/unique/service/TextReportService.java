package com.xevgnov.unique.service;

import com.xevgnov.unique.service.ReportService;

public class TextReportService implements ReportService {

    @Override
    public void printReport(String input) {
       System.out.println(input);        
    }
    
}
