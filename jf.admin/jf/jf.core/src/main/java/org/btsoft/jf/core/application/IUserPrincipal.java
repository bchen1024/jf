package org.btsoft.jf.core.application;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @ClassName IUserPrincipal
 * @Description 用户接口类
 * @author bchen
 * @created 2013-11-12 18:47:12
 */
public interface IUserPrincipal {

	public Long getUserId();

	public String getUserName();

	public String getUserAccount();
	
	public String getUserPassword();

	public String getUserEmail();
	
	public int getPasswordError();
	
	public Date getLockTime();
	
	public List<String> getPermissions();
	
	public Map<String,Object> getSettings();
	
	public List<Map<String,Object>> getMenus();
	
	public Map<String,Object> getGlobal();
	
}
