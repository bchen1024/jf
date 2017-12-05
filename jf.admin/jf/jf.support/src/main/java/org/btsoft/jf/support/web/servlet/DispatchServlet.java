
package org.btsoft.jf.support.web.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.content.JFApplicationContext;
import org.btsoft.jf.support.web.util.ResponseUtil;

/**
 * @ClassName DispatchServlet
 * @Description servlet转发器
 * @author bchen
 * @created 2013-12-9 23:52:16
 */
public class DispatchServlet extends HttpServlet {

	private static final long serialVersionUID = -4114237433641056257L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Servlet servlet = this.genServlet(req, resp);
		if (servlet != null) {
			servlet.service(req, resp);
		}
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.service(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.service(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.service(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.service(req, resp);

	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		Map<String, Servlet> servlets = JF.getContext().getBeansOfType(Servlet.class);
		for (Servlet servlet : servlets.values()) {
			servlet.init(config);
		}
	}

	private Servlet genServlet(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		HttpServletRequest htmlReq = (HttpServletRequest) req;
		HttpServletResponse htmlResp = (HttpServletResponse) resp;

		// servlet bean名称
		String uri = htmlReq.getRequestURI();
		String contextPath = htmlReq.getContextPath();
		String beanName = uri.substring(contextPath.length());

		JFApplicationContext application = JF.getContext();
		// 检查bean是否存在
		if (!application.containsBean(beanName)) {
			ResponseUtil.printException(htmlResp, "common.exception.beanNotFound", new Object[] { beanName });
			return null;
		}
		return application.getBean(beanName, Servlet.class);
	}
}
