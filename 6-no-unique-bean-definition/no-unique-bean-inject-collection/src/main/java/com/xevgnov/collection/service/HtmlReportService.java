package com.xevgnov.collection.service;
import org.springframework.stereotype.Service;
import com.xevgnov.collection.service.ReportService;

@Service
public class HtmlReportService implements ReportService {

    @Override
    public void printReport(String input) {
        System.out.println(String.format("<!DOCTYPE HTML>" +
                "<html><div>%s</div></html>", input));
    }

}
