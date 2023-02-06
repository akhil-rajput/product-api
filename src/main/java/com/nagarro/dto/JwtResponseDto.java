package com.nagarro.dto;

public class JwtResponseDto {

	String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JwtResponseDto(String token) {
		this.token = token;
	}

	public JwtResponseDto() {

	}

}
