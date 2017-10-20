package org.btsoft.jf.core.application;

import java.util.Map;

/**
 * @ClassName IRequestContext
 * @Description 当前应用上下文
 * @author bchen
 * @created 2013-12-13 21:44:21
 */
public interface IRequestContext {

	public IApplication getApplication();

	public IUserPrincipal getUser();

	public String getCurrentLanguage();

	public String getHostIp();

	public String getHostName();

	public Map<String, Object> getItems();

}
