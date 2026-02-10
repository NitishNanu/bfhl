package com.chitkara.bfhl.controller;

import com.chitkara.bfhl.dto.ApiResponse;
import com.chitkara.bfhl.service.AiService;
import com.chitkara.bfhl.service.BfhlService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class BfhlController {

    private static final String EMAIL = "your_email@chitkara.edu.in";

    private final BfhlService bfhlService;
    private final AiService aiService;

    public BfhlController(BfhlService bfhlService, AiService aiService) {
        this.bfhlService = bfhlService;
        this.aiService = aiService;
    }

    @PostMapping("/bfhl")
    public ResponseEntity<ApiResponse<?>> bfhl(@RequestBody Map<String, Object> input) {
        Object result = bfhlService.process(input, aiService);
        return ResponseEntity.ok(new ApiResponse<>(true, EMAIL, result));
    }

    @GetMapping("/health")
    public ResponseEntity<ApiResponse<?>> health() {
        return ResponseEntity.ok(new ApiResponse<>(true, EMAIL, null));
    }
}
