package org.btsoft.jf.core.application;

/**
 * @ClassName IApplication
 * @Description 应用程序接口
 * @author bchen
 * @created 2017年6月29日 下午10:56:54
 */
public interface IApplication {

	public String getAppName();

	public String getVersion();

	public String getContextPath();

	public String getEnvironment();
	
	public String getLanguage();
}
