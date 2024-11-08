package com.xevgnov.unique.service;
import org.springframework.stereotype.Service;
import com.xevgnov.unique.service.ReportService;

@Service
public class HtmlReportService implements ReportService {

    @Override
    public void printReport(String input) {
        System.out.println(String.format("<!DOCTYPE HTML>" +
                "<html><div>%s</div></html>", input));
    }

}
