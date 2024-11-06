package com.xevgnov.stereotypes.controller;

import com.xevgnov.stereotypes.annotaton.InfoRestController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@InfoRestController
public class InfoController {

    @Value("${application.name}")
    private String applicationName;

    @Value("${build.version}")
    private String buildVersion;

    @Value("${build.timestamp}")
    private String buildTimestamp;

    @GetMapping
    public String getAppInfo() {
        return String.format("Application name: %s\nBuild Version: %s\nBuild Timestamp: %s", 
        applicationName, buildVersion, buildTimestamp);
    }

}
