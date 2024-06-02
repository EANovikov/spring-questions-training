package com.xevgnov.profiles.service;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.xevgnov.profiles.service.ReportService;

@Profile("test")
@Service
public class TextReportService implements ReportService {

    @Override
    public void printReport(String input) {
       System.out.println(input);        
    }
    
}
