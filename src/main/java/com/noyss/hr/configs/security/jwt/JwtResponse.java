package com.noyss.hr.configs.security.jwt;

import lombok.Data;

@Data
public class JwtResponse {
    private String accessToken;
    private String tokenType = "Bearer";
    private String tokenKey = "Authorization";

    public JwtResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
