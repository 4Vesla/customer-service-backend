package com.it.revolution.customer.service.app.security.jwt.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
