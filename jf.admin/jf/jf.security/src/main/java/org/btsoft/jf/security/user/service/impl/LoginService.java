package org.btsoft.jf.security.user.service.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.core.common.ILoginService;
import org.btsoft.jf.security.user.dao.IUserDao;
import org.btsoft.jf.security.user.entity.UserVO;

/**
 * @ClassName LoginService
 * @Description 用户登录实现类
 * @author bchen
 * @created 2017年11月12日 下午11:44:06
 */
@Named
public class LoginService implements ILoginService {
	
	@Inject
	private IUserDao dao;

	@Override
	public UserVO findUserByAccount(String userAccount) {
		UserVO user=new UserVO();
		user.setUserAccount(userAccount);
		return dao.findUserByAccount(user);
	}

	@Override
	public void updatePasswordErrorNumber(Map<String, Object> user) {
		dao.updatePasswordErrorNumber(user);
	}

}
