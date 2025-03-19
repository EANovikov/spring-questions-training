package com.xevgnov.unique.service;

import org.springframework.stereotype.Service;

@Service
public class ReportProcessorServiceImpl implements ReportProcessorService {

    private final ReportService reportService;

    public ReportProcessorServiceImpl(ReportService reportService) {
        this.reportService = reportService;
    }

    @Override
    public void process(String data) {
        reportService.printReport("test report data");
    }
}
