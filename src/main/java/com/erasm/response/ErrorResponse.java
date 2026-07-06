package com.erasm.response;

import java.time.LocalDateTime;

public class ErrorResponse {
	private LocalDateTime timsestamp;
	private int status;
	private String error;
	private String message;
	
	public ErrorResponse() {
		
	}

	public ErrorResponse(LocalDateTime timsestamp, int status, String error, String message) {
		super();
		this.timsestamp = timsestamp;
		this.status = status;
		this.error = error;
		this.message = message;
	}

	public LocalDateTime getTimsestamp() {
		return timsestamp;
	}

	public void setTimsestamp(LocalDateTime timsestamp) {
		this.timsestamp = timsestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	

}
