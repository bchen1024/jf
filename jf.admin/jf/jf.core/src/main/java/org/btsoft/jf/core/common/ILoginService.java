package org.btsoft.jf.core.common;

import java.util.Map;

import org.btsoft.jf.core.application.IUserPrincipal;

/**
 * @ClassName ILoginService
 * @Description 登录Service接口
 * @author bchen
 * @created 2017年11月14日 下午12:21:55
 */
public interface ILoginService {

	/**
	 * @Description 根据账号查询用户
	 * @param userAccount
	 * @return
	 * @author bchen
	 * @created 2017年11月12日 下午11:43:33
	 */
	public IUserPrincipal findUserByAccount(String userAccount);
	
	/**
	 * @Description 更新密码错误次数
	 * @param user
	 * @author bchen
	 * @created 2017年11月14日 下午12:32:39
	 */
	public void updatePasswordErrorNumber(Map<String, Object> user);
	
}
