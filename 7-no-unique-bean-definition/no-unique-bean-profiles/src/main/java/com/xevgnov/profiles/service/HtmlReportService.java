package com.xevgnov.profiles.service;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.xevgnov.profiles.service.ReportService;

@Profile("prod")
@Service
public class HtmlReportService implements ReportService {

    @Override
    public void printReport(String input) {
        System.out.println(String.format("<!DOCTYPE HTML>" +
                "<html><div>%s</div></html>", input));
    }

}
