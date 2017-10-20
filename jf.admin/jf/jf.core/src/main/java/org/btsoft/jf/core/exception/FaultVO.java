package org.btsoft.jf.core.exception;

import java.io.Serializable;

public class FaultVO implements Serializable {

	private static final long serialVersionUID = 6249769525288153213L;

	private int httpCode;
	private String errorCode;
	private String message;

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
