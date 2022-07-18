package com.it.revolution.customer.service.app.common;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ControllerUtil {

    public static ResponseEntity<Map<String, String>> getErrorResponse(Exception exception, HttpStatus status) {
        String timestampNow = new SimpleDateFormat("yyyy-MM-dd HH:mmZ").format(new Date());
        Map<String, String> result = new HashMap<>();
        result.put("timestamp", timestampNow);
        result.put("error", exception.getMessage());

        return ResponseEntity.status(status)
                .body(result);
    }

}
