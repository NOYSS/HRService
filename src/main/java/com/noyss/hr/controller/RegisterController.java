package com.noyss.hr.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {

    @PostMapping("/validateAndSave")
    public String validateAndSave(@RequestBody String dataJson){
        return null;
    }

}
