package com.chitkara.bfhl.dto;

public class ApiResponse<T> {
	private boolean is_success;
	private String official_email;
	private T data;
	
	public ApiResponse(boolean is_success,String official_email,T data) {
		this.is_success = is_success;
		this.official_email = official_email;
		this.data = data;
	}
	
	public boolean isIs_success() {
		return is_success;
	}
	
	public String getOffical_email() {
		return official_email;
	}
	
	public T getData() {
		return data;
	}
}
