package com.example.backend.service;

import org.springframework.stereotype.Service;

@Service
public class JWTService {
    public String generateToken() {
        return "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6InJhdmluZHUiLCJpYXQiOjE1MTYyMzkwMjJ9.hhv_Ck632wSUj1Rhde1ofnSMmj9XQAmDRZHKm-XMY9o";
    }
}
