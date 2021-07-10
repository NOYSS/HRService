package com.noyss.hr.configs.startup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.name}")
    private String appName;

    @Value("${spring.profiles.active}")
    private String appProfile;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        ParameterStartup.appVersion = appVersion;
        ParameterStartup.appName = appName;
        ParameterStartup.appProfile = appProfile;

        LOGGER.info("name : {}", appName);
        LOGGER.info("version : {}", appVersion);
        LOGGER.info("app profile : {}", appProfile);
    }
}
