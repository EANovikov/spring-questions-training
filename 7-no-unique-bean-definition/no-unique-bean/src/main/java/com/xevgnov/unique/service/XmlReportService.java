package com.xevgnov.unique.service;
import org.springframework.stereotype.Service;
import com.xevgnov.unique.service.ReportService;

@Service
public class XmlReportService implements ReportService {

    @Override
    public void printReport(String input) {
        System.out.println(String.format("<report>" +
                "<text>%s</text></report>", input));
    }

}
