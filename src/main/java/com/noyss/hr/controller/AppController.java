package com.noyss.hr.controller;

import com.noyss.hr.configs.startup.ParameterStartup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    private final Logger LOGGER = LogManager.getLogger(this.getClass());

    @Autowired
    PasswordEncoder encoder;

    @GetMapping("/appName")
    public String appName(){
        LOGGER.info("Info");
        LOGGER.debug("debug");
        LOGGER.error("error");
        LOGGER.warn("warn");
        return  ParameterStartup.appName;
    }

    @GetMapping("/appVersion")
    public String appVersion(){
        return  ParameterStartup.appVersion;
    }

    @GetMapping("/appProfile")
    public String appProfile(){
        return  ParameterStartup.appProfile;
    }

    @GetMapping("/encoder")
    public String appProfile(@RequestParam("key") String key){
        return  encoder.encode(key);
    }

}
