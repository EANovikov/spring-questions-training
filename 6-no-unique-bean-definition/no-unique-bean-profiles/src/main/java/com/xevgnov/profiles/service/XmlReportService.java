package com.xevgnov.profiles.service;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import com.xevgnov.profiles.service.ReportService;

@Profile("dev")
@Service
public class XmlReportService implements ReportService {

    @Override
    public void printReport(String input) {
        System.out.println(String.format("<report>" +
                "<text>%s</text></report>", input));
    }

}
