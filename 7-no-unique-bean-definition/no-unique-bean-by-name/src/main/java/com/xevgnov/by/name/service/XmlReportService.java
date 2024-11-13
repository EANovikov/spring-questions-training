package com.xevgnov.by.name.service;
import org.springframework.stereotype.Service;

@Service
public class XmlReportService implements ReportService {

    @Override
    public void printReport(String input) {
        System.out.println(String.format("<report>" +
                "<text>%s</text></report>", input));
    }

}
