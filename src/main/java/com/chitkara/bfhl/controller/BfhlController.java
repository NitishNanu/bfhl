package com.chitkara.bfhl.controller;

import com.chitkara.bfhl.dto.ApiResponse;
import com.chitkara.bfhl.service.AiService;
import com.chitkara.bfhl.service.BfhlService;
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
    public ApiResponse<?> bfhl(@RequestBody Map<String, Object> input) {
        Object result = bfhlService.process(input, aiService);
        return new ApiResponse<>(true, result, EMAIL);
    }

    @GetMapping("/health")
    public ApiResponse<?> health() {
        return new ApiResponse<>(true, null, EMAIL);
    }
}
