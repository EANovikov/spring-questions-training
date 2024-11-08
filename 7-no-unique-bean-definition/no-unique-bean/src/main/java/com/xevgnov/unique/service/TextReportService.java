package com.xevgnov.unique.service;
import org.springframework.stereotype.Service;
import com.xevgnov.unique.service.ReportService;

@Service
public class TextReportService implements ReportService {

    @Override
    public void printReport(String input) {
       System.out.println(input);        
    }
    
}
