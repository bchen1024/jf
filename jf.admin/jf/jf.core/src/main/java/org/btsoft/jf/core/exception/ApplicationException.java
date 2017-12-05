package org.btsoft.jf.core.exception;

import java.io.Serializable;

/**
 * @ClassName BusinessException
 * @Description 自定义业务异常
 * @author bchen
 * @created 2013-11-8 10:37:12
 */
public class ApplicationException extends Exception implements Serializable {

	private static final long serialVersionUID = -7064541762953787503L;

	private String errorCode="exception.message.systemException";
	private Object[] args;
	private int httpCode=500;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public Object[] getArgs() {
		return args;
	}

	public ApplicationException() {
		super();
	}

	public ApplicationException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}

	public ApplicationException(Object[] args) {
		super();
		this.args = args;

	}

	public ApplicationException(String errorCode, Object[] args) {
		super(errorCode);
		this.errorCode = errorCode;
		this.args = args;
	}
	
	public ApplicationException(int httpCode,String errorCode) {
		super(errorCode);
		this.httpCode=httpCode;
		this.errorCode = errorCode;
	}
	
	public ApplicationException(int httpCode,String errorCode, Object[] args) {
		super(errorCode);
		this.httpCode=httpCode;
		this.errorCode = errorCode;
		this.args = args;
	}

	public ApplicationException(Throwable throwable, String errorCode,
			String[] args) {
		super(errorCode, throwable);
		this.errorCode = errorCode;
		this.args = args;
	}

	@Override
	public String getMessage() {
		return ExceptionUtils.createExceptionMessage(errorCode, args);
	}

	public int getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(int httpCode) {
		this.httpCode = httpCode;
	}

}
