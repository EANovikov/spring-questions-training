package com.xevgnov.by.name.service;

import org.springframework.stereotype.Service;

@Service("text")
public class TextReportService implements ReportService {

    @Override
    public void printReport(String input) {
        System.out.println("*** " + input + " ***");
    }

}
