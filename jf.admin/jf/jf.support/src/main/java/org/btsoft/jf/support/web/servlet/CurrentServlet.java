package org.btsoft.jf.support.web.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.btsoft.jf.core.content.ICurrentService;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.content.RequestContext;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @ClassName CurrentServlet
 * @Description 当前对象信息
 * @author bchen
 * @created 2017年7月2日 下午5:01:01
 */
@Named("/servlet/current")
public class CurrentServlet extends HttpServlet {
	private static final long serialVersionUID = 185814163920579359L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("application", RequestContext.getCurrent().getApplication());
		result.put("user", RequestContext.getCurrent().getUser());

		Map<String, ICurrentService> current = JF.getContext().getBeansOfType(ICurrentService.class);
		for (Iterator<ICurrentService> iterator = current.values().iterator(); iterator.hasNext();) {
			ICurrentService currentService = iterator.next();
			Map<String, Object> currentResult = currentService.findCurrentData();
			result.putAll(currentResult);

		}

		ObjectMapper mapper = new ObjectMapper();
		ServletOutputStream os = resp.getOutputStream();
		mapper.writeValue(os, result);
		os.flush();
		os.close();
	}

}
