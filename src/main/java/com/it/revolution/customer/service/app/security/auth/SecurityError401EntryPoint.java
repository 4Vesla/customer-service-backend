package com.it.revolution.customer.service.app.security.auth;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SecurityError401EntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType("application/json");
        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mmZ").format(new Date());;
        response.getWriter().write(String.format("{ \"error\": \"You are not authenticated.\", \"timestamp\": \"%s\" }", timestamp));
    }
}
