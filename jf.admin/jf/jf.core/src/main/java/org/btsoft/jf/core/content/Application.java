package org.btsoft.jf.core.content;

import org.btsoft.jf.core.application.IApplication;

/**
 * @ClassName Application
 * @Description 应用程序
 * @author bchen
 * @created 2013-11-25 10:38:06
 */
public class Application implements IApplication {

	private String contextPath;// 系统根路径
	private String appName;// 应用名称
	private String environment;// 当前环境
	private String version;//当前版本
	private String language;//当前语言

	private static IApplication application;

	public static IApplication getCurrent() {
		if (application != null) {
			return application;
		}
		application = JF.getContext().getBean("application", IApplication.class);
		return application;
	}

	@Override
	public String getContextPath() {
		return this.contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	@Override
	public String getAppName() {
		return this.appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getEnvironment() {
		return environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	@Override
	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
