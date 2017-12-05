package org.btsoft.jf.support.web.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.btsoft.jf.core.content.RequestContext;
import org.btsoft.jf.core.utils.JsonUtils;
import org.btsoft.jf.core.utils.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * @ClassName RequestUtil
 * @Description 请求工具类
 * @author bchen
 * @created 2015-2-16 下午9:31:34
 */
public class RequestUtil {

	public static final String LOGIN_HTML = "/login.html";
	public static final String PUBLIC_RESOURCES = "/pub/";
	public static final String PUBLIC_SERVLET = "/servlet/public/";

	public static final String STATIC_RESOURCES = "html,js,css,jpg,png,gif,ico,woff2,ttf";

	public static Logger logger = Logger.getLogger(RequestUtil.class);

	/**
	 * @Description 获取ContextPath
	 * @param req
	 * @return
	 * @author bchen
	 * @created 2013-12-14 20:55:44
	 */
	public static String getContextPath(HttpServletRequest req) {
		return req.getContextPath();
	}

	/**
	 * @Description 判断是否为ajax请求
	 * @param req
	 * @return
	 * @author bchen
	 * @created 2017-2-5 11:22:44
	 */
	public static boolean isAjax(HttpServletRequest req) {
		String requestType = req.getHeader("X-Requested-With");
		return "XMLHttpRequest".equals(requestType) ? true : false;
	}

	/**
	 * @Description 跳转到登录页面
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @author bchen
	 * @created 2013-12-14 21:53:29
	 */
	public static void sendRedirectLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(getContextPath(req) + LOGIN_HTML + "?requestURI=" + req.getRequestURI());
	}

	/**
	 * @Description 跳转到首页
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @author bchen
	 * @created 2013-12-14 21:53:55
	 */
	public static void sendRedirectIndex(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.sendRedirect(getContextPath(req));
	}

	/**
	 * @Description 获取Servelet服务名称
	 * @param req
	 * @return
	 * @author bchen
	 * @created 2013-12-14 21:54:10
	 */
	public static String getServletName(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String contextPath = getContextPath(req);
		return uri.substring(contextPath.length());
	}

	/**
	 * @Description 请求参数转换成Map
	 * @param req
	 * @return
	 * @author bchen
	 * @created 2017-6-3 下午11:28:20
	 */
	public static Map<String, String> getRequestParams(HttpServletRequest req) {
		Map<String, String> paramsMap = new HashMap<String, String>();
		Enumeration<String> params = req.getParameterNames();
		if (params != null) {
			while (params.hasMoreElements()) {
				String param = params.nextElement();
				String value = req.getParameter(param);
				paramsMap.put(param, value);
			}
		}
		return paramsMap;
	}

	/**
	 * @Description 获取当前ip地址
	 * @param request
	 * @return
	 * @author bchen
	 * @created 2017-5-27 下午10:24:41
	 */
	public static String getRemortIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				ip = "Unknown";
			}
		}
		return ip;
	}

	/**
	 * @Description 是否为公共资源，无需登录即可访问
	 * @param req
	 * @param uri
	 * @return
	 * @author bchen
	 * @created 2017-5-27 下午9:50:31
	 */
	public static boolean isPublicResources(HttpServletRequest req) {
		String uri = req.getRequestURI().toLowerCase();
		String contentPath = getContextPath(req);
		if (uri.startsWith(contentPath + PUBLIC_RESOURCES)) {
			return true;
		}
		return false;
	}

	/**
	 * @Description 是否为公共Servlet，无需登录即可访问
	 * @param req
	 * @param uri
	 * @return
	 * @author bchen
	 * @created 2017-5-27 下午9:50:31
	 */
	public static boolean isPublicServlet(HttpServletRequest req) {
		String uri = req.getRequestURI().toLowerCase();
		String contentPath = getContextPath(req);
		if (uri.startsWith(contentPath + PUBLIC_SERVLET)) {
			return true;
		}
		return false;
	}

	// 构建RequestContext对象
	public static void buildRequestContext(HttpServletRequest req, RequestContext rc) throws UnknownHostException {
		String hostIP = RequestUtil.getRemortIP(req);
		InetAddress inet = InetAddress.getByName(hostIP);
		rc.setHostIp(inet.getHostAddress());
		rc.setHostName(inet.getHostName());
		rc.setClient(RequestUtil.getClient(req));
		rc.setCurrentLanguage(req.getHeader("currentLanguage"));
		rc.getItems().put("requestUrl", req.getRequestURI());
		req.getSession().setAttribute("userSession", rc);
	}

	public static String getClient(HttpServletRequest req) {
		String client=req.getHeader("Client");
		if(StringUtils.isEmpty(client)) {
			String ua=req.getHeader("User-Agent");
			if(ua.contains("Android")) {
				client="Android";
			}else if(ua.contains("iPhone")) {
				client="iPhone";
			}else if(ua.contains("iPad")) {
				client="iPad";
			}else if(ua.contains("Windows Phone")) {
				client="Windows Phone";
			}else {
				return "PC";
			}
		}
		return client;
	}
	
	public static void allowCrossDomain(HttpServletResponse resp) {
		resp.setCharacterEncoding("utf-8");
		resp.setHeader("Access-Control-Allow-Origin","*");
		resp.setHeader("Access-Control-Allow-Methods","POST,GET,DELETE,PUT,OPTIONS,HEAD");
		resp.setHeader("Access-Control-Max-Age", "3600");  
		resp.setHeader("Content-Type", "application/json");
		resp.setHeader("Access-Control-Allow-Headers", "token,currentLanguage,Content-Type,Access-Control-Allow-Origin,Access-Control-Allow-Methods,Access-Control-Max-Age,Authorization");
	}
	
	public static String getCookieValue(HttpServletRequest req,String name) {
		Cookie[] cookies=req.getCookies();
		if(cookies!=null && cookies.length>0) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}
	
	/**
	 * @Description 判断是否为鉴权请求，如果是鉴权请求，无需token
	 * @param req
	 * @return
	 * @author bchen
	 * @created 2017年11月22日 下午10:42:26
	 */
	public static boolean isAuthRequest(HttpServletRequest req) {
		String uri = req.getRequestURI();
		return uri.startsWith(getContextPath(req)+"/auth/");
	}
	
	public static Map<String,String> getParameterMap(HttpServletRequest req) throws IOException{
		ObjectMapper mapper=new ObjectMapper();
		ServletInputStream is= req.getInputStream();
		Map<String,String> result=mapper.readValue(is, Map.class);
		is.close();
		return result;
	}
}
