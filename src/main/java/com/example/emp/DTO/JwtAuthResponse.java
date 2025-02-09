package com.example.emp.DTO;

import lombok.Getter;


public class JwtAuthResponse {

	
	private String token;
	private String tokenType ="Bearer";
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public JwtAuthResponse (String token)
	{
		this.token=token;
	}
}
