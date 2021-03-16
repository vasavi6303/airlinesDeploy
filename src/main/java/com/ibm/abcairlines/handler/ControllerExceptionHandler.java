package com.ibm.abcairlines.handler;

public class ControllerExceptionHandler {
	
	private String error;
	
	public ControllerExceptionHandler() {
		
	}

	public ControllerExceptionHandler(String error) {
		this.error = error;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	

}
