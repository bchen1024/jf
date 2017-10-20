package org.btsoft.jf.core.cloud;

import org.btsoft.jf.core.exception.ApplicationException;

public interface ICloudService {
	
	public Object getBean();

	public Object invoke(String methodName) throws ApplicationException;
	
	public Object invoke(String methodName,Object...params) throws ApplicationException;
}
