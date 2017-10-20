package org.btsoft.jf.core.content;

import java.util.HashMap;
import java.util.Map;

import org.btsoft.jf.core.application.IApplication;
import org.btsoft.jf.core.application.IRequestContext;
import org.btsoft.jf.core.application.IUserPrincipal;
import org.btsoft.jf.core.cache.JFCache;
import org.btsoft.jf.core.constants.CoreConstant;

/**
 * @ClassName RequestContext
 * @Description 应用上下文
 * @author bchen
 * @created 2013-12-14 8:03:12
 */
public class RequestContext implements IRequestContext {
	private IUserPrincipal user;
	private IApplication application;
	private String hostIp;
	private String hostName;
	private String client;
	private String currentLanguage;
	private Map<String, Object> items = new HashMap<String, Object>();

	public static RequestContext getCurrent() {
		RequestContext rc = RequestManagement.getCurrent();
		return rc;
	}
	
	public static RequestContext getCurrent(boolean isNull) {
		RequestContext rc = RequestManagement.getCurrent();
		if(rc==null && isNull){
			rc=new RequestContext();
		}
		return rc;
	}

	public static void setCurrent(RequestContext current) {
		RequestManagement.setCurrent(current);
	}

	public void setUser(IUserPrincipal user) {
		this.user = user;
	}

	@Override
	public IUserPrincipal getUser() {
		return user;
	}

	public String getCurrentLanguage() {
		if (this.currentLanguage == null) {
			this.currentLanguage = JFCache.getCahce("property", "currentLanguage", CoreConstant.LANGUAGE_ZH_CN);
		}
		return this.currentLanguage;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getHostName() {
		return hostName;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
	}

	@Override
	public IApplication getApplication() {
		if(this.application==null){
			this.application=Application.getCurrent();
		}
		return this.application;
	}

	public void setApplication(IApplication application) {
		this.application = application;
	}

	public Map<String, Object> getItems() {
		return items;
	}

	public void setItems(Map<String, Object> items) {
		this.items = items;
	}
}
