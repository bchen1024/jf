package org.btsoft.jf.support.web.listener;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.cache.ICache;
import org.btsoft.jf.core.content.Application;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.file.IDownloadSupport;
import org.btsoft.jf.core.file.IUploadSupport;

/**
 * @ClassName InitApplicationListener
 * @Description 应用程序监听器
 * @author bchen
 * @created 2013-11-10 3:42:05
 */
public class ApplicationListener implements ServletContextListener {

	private static Logger logger = Logger.getLogger(ApplicationListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		try {
			this.initApplication(event.getServletContext());
			this.initBean(event.getServletContext());
		} catch (Exception e) {
			logger.error(e);
		}
	}

	/**
	 * @Description 初始化应用信息
	 * @param sc
	 * @author bchen
	 * @throws ApplicationException
	 * @created 2013-11-26 10:19:19
	 */
	private void initApplication(ServletContext sc) throws ApplicationException {
		Application application = (Application) Application.getCurrent();
		application.setContextPath(sc.getContextPath());
		String profiles = sc.getInitParameter("spring.profiles.active");
		String environment = profiles != null ? profiles : "dev";
		application.setEnvironment(environment);
		logger.info("Application set appName：" + application.getAppName());
		logger.info("Application set contextPath：" + application.getContextPath());
		logger.info("Application set Environment：" + application.getEnvironment());
		logger.info("Application set Version：" + application.getVersion());
	}

	/**
	 * @Description 统计系统业务Bean数量
	 * @param sc
	 * @author bchen
	 * @created 2014-6-25 下午8:09:36
	 */
	private void initBean(ServletContext sc) throws ApplicationException {
		// 统计业务bean
		Map<String, IRestService> beans = JF.getContext().getBeansOfType(IRestService.class);
		for (Iterator<String> iterator = beans.keySet().iterator(); iterator.hasNext();) {
			logger.info("[Business Bean：" + iterator.next() + "]");
		}

		// 统计下载Bean
		Map<String, IDownloadSupport> downloads = JF.getContext().getBeansOfType(IDownloadSupport.class);
		for (Iterator<String> iterator = downloads.keySet().iterator(); iterator.hasNext();) {
			logger.info("[Download Bean：" + iterator.next() + "]");
		}

		// 统计上传Bean
		Map<String, IUploadSupport> uploads = JF.getContext().getBeansOfType(IUploadSupport.class);
		for (Iterator<String> iterator = uploads.keySet().iterator(); iterator.hasNext();) {
			logger.info("[Upload Bean：" + iterator.next() + "]");
		}

		// 统计Servlet Bean
		Map<String, HttpServlet> servlets = JF.getContext().getBeansOfType(HttpServlet.class);
		for (Iterator<String> iterator = servlets.keySet().iterator(); iterator.hasNext();) {
			logger.info("[Servlet Bean：" + iterator.next() + "]");
		}

		// 统计Cache Bean
		Map<String, ICache> caches = JF.getContext().getBeansOfType(ICache.class);
		for (Iterator<String> iterator = caches.keySet().iterator(); iterator.hasNext();) {
			logger.info("[Cache Bean：" + iterator.next() + "]");
		}

	}
}
