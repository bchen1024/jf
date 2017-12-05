package org.btsoft.jf.support.web.filter;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.btsoft.jf.core.content.RequestContext;
import org.btsoft.jf.support.web.util.RequestUtil;

/**
 * @ClassName RequestFilter
 * @Description 请求过滤器
 * @author bchen
 * @created 2017年7月2日 下午5:05:49
 */
public class RequestFilter implements Filter {

	private final static Logger logger=Logger.getLogger(RequestFilter.class);
	
	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			HttpServletResponse resp = (HttpServletResponse) response;
			chain.doFilter(req, resp);
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
