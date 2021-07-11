package com.noyss.hr.controller;

import com.noyss.hr.configs.security.LoginForm;
import com.noyss.hr.configs.security.jwt.JwtProvider;
import com.noyss.hr.configs.security.jwt.JwtResponse;
import com.noyss.hr.configs.startup.ParameterStartup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AppController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

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

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }
}
