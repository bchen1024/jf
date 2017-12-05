package org.btsoft.jf.core.exception;

/**
 * @ClassName SecurityException
 * @Description 无权限异常
 * @author bchen
 * @created 2017年11月12日 下午6:31:32
 */
public class JFSecurityException extends ApplicationException {

	private static final long serialVersionUID = 4402772258420760951L;
	
	public JFSecurityException() {
		super(401, "security.exception.401");
	}

}
