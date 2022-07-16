package com.it.revolution.customer.service.app;

import com.it.revolution.customer.service.app.amazon.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("app/file")
@RequiredArgsConstructor
public class TestController {

    private final FileService fileService;

    @PostMapping
    public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("file")MultipartFile file) {
        String publicUrl = fileService.uploadFile(file);

        Map<String, String> response = new HashMap<>();
        response.put("publicUrl", publicUrl);

        return new ResponseEntity<Map<String, String>>(response, HttpStatus.CREATED);
    }
}
