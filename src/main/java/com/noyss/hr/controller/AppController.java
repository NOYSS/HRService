package com.noyss.hr.controller;

import com.noyss.hr.configs.startup.ParameterStartup;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/appName")
    public String appName(){
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
}
