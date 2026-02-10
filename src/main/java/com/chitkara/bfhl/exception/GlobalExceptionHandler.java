package com.chitkara.bfhl.exception;

import com.chitkara.bfhl.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String EMAIL = "your_email@chitkara.edu.in";

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handle(Exception e) {
        return new ApiResponse<>(false, e.getMessage(), EMAIL);
    }
}
