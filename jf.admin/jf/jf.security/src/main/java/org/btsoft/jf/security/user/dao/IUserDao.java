package org.btsoft.jf.security.user.dao;

import java.util.List;
import java.util.Map;

import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.security.user.entity.UserVO;

/**
 * @ClassName IUserDao
 * @Description 用户管理Dao接口
 * @author bchen
 * @created 2013-11-20 12:00:54
 */
public interface IUserDao {

	/**
	 * @Description 根据账号和密码
	 * @param user
	 * @return
	 * @author bchen
	 * @created 2013-12-9 9:55:07
	 */
	UserVO findUserByAccount(UserVO user);

	/**
	 * @Description 分页查询用户信息
	 * @param user
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2013-12-20 18:50:06
	 */
	PagedResult<UserVO> findUserList(UserVO user, PageVO page);

	/**
	 * @Description 创建用户信息
	 * @param user
	 * @author bchen
	 * @created 2013-12-5 9:10:15
	 */
	int createUser(UserVO user);

	/**
	 * @Description 获取单个用户信息
	 * @param user
	 * @return
	 * @author bchen
	 * @created 2013-12-5 9:10:52
	 */
	UserVO findUser(UserVO user);

	/**
	 * @Description 更新单个用户信息
	 * @param user
	 * @return
	 * @author bchen
	 * @created 2013-12-5 9:11:22
	 */
	int updateUser(UserVO user);

	/**
	 * @Description 删除单个用户信息
	 * @param user
	 * @return
	 * @author bchen
	 * @created 2013-12-9 7:26:02
	 */
	int deleteUser(UserVO user);
	
	/**
	 * @Description 查询用户
	 * @param user
	 * @return
	 * @author bchen
	 * @created 2016-10-22 下午11:55:49
	 */
	List<UserVO> queryUser(UserVO user);
	
	/**
	 * @Description 更新密码错误次数
	 * @param user
	 * @return
	 * @author bchen
	 * @created 2017年11月15日 下午10:48:14
	 */
	int updatePasswordErrorNumber(Map<String, Object> user);
}
