package com.xevgnov.stereotypes.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;

import com.xevgnov.stereotypes.annotaton.InfoRestController;

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
