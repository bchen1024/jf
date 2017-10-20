package org.btsoft.jf.core.entity;

import java.io.Serializable;

/**
 * @ClassName ResultVO
 * @Description 结果VO
 * @author bchen
 * @created 2017-6-8 下午9:34:33
 */
public class ResultVO implements Serializable {
	
	private static final long serialVersionUID = -4132623198624347310L;

	private int status;
	
	private String message;
	
	private Object result;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
}
