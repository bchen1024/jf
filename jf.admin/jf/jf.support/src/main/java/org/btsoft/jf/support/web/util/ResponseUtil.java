/**
 * @FileName ResponseUtil.java
 * @Package com.jf.web.support.util
 * @Description 响应工具类
 * @author bchen   
 * @version 1.0  
 * @created 2017-6-3 下午9:35:13 
 */
package org.btsoft.jf.support.web.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.utils.JsonUtils;

/**
 * @ClassName ResponseUtil
 * @Description 响应工具类
 * @author bchen
 * @created 2017-6-3 下午9:35:13
 */
public class ResponseUtil {
	
	private final static Logger logger=Logger.getLogger(ResponseUtil.class);

	public static void printValue(HttpServletResponse resp,Object value) throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		try {
			resp.setContentType(WebSupport.JSON_CONTENT_TYPE);
			JsonUtils.writeValue(out, value);
		} finally {
			out.flush();
			out.close();
		}
	}
	
	public static void printValue(HttpServletResponse resp,String value) throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		try {
			resp.setContentType(WebSupport.HTML_CONTENT_TYPE);
			out.print(value);
		} finally {
			out.flush();
			out.close();
		}
	}
	
	public static void printException(HttpServletResponse resp,ApplicationException e) throws IOException {
		ServletOutputStream out = resp.getOutputStream();
		try {
			Map<String, Object> mapException=new HashMap<String, Object>();
			mapException.put("errorCode", e.getErrorCode());
			mapException.put("httpCode", e.getHttpCode());
			mapException.put("message", e.getMessage());
			resp.setStatus(e.getHttpCode());
			resp.setContentType(WebSupport.JSON_CONTENT_TYPE);
			JsonUtils.writeValue(out, mapException);
			logger.error(e);
		} finally {
			out.flush();
			out.close();
		}
	}
	
	public static void printException(HttpServletResponse resp,String errorCode) throws IOException {
		ApplicationException e=new ApplicationException(errorCode);
		printException(resp, e);
	}
	
	public static void printException(HttpServletResponse resp,String errorCode,Object[] args) throws IOException {
		ApplicationException e=new ApplicationException(errorCode,args);
		printException(resp, e);
	}
	
	public static void printException(HttpServletResponse resp,String errorCode,int httpCode) throws IOException {
		ApplicationException e=new ApplicationException(httpCode,errorCode);
		printException(resp, e);
	}
	
	public static void printException(HttpServletResponse resp,String errorCode,int httpCode,Object[] args) throws IOException {
		ApplicationException e=new ApplicationException(httpCode,errorCode,args);
		printException(resp, e);
	}
}
