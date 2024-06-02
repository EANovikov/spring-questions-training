package com.xevgnov.primary.service;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import com.xevgnov.primary.service.ReportService;

@Primary
@Service
public class HtmlReportService implements ReportService {

    @Override
    public void printReport(String input) {
        System.out.println(String.format("<!DOCTYPE HTML>" +
                "<html><div>%s</div></html>", input));
    }

}
