package org.bytemark.bytewheels.models;

/**
 * 
 * @author ddhar
 * 
 * POJO for representing Exceptions Thrown
 * By the application and providing client with a 
 * compact JSON Object that conveys proper
 * Message
 *
 */

public class ErrorMessage {

	private String errorMessage;
	private int errorCode;
	
	public ErrorMessage(){
		
	}

	public ErrorMessage(String errorMessage, int errorCode) {
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}
	@Override
	public String toString() {
		return "ErrorMessage [errorMessage=" + errorMessage + ", errorCode="
				+ errorCode + "]";
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

}
