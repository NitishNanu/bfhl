package com.chitkara.bfhl.dto;

public class ApiResponse<T> {

    private boolean is_success;
    private T data;
    private String offical_email;

    public ApiResponse(boolean is_success, T data, String offical_email) {
        this.is_success = is_success;
        this.data = data;
        this.offical_email = offical_email;
    }

    public boolean isIs_success() {
        return is_success;
    }

    public T getData() {
        return data;
    }

    public String getOffical_email() {
        return offical_email;
    }
}
