package org.btsoft.jf.security.role.dao;

import java.util.List;

import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.security.role.entity.RoleVO;

/**
 * @ClassName IRoleDao
 * @Description 角色管理Dao接口
 * @author bchen
 * @created 2013-11-5 7:34:50
 */
public interface IRoleDao {

	/**
	 * @Description 分页查询角色信息
	 * @param role
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2013-11-7 11:07:06
	 */
	PagedResult<RoleVO> findRoleList(RoleVO role, PageVO page);

	/**
	 * @Description 创建角色
	 * @param role
	 * @return
	 * @author bchen
	 * @created 2014-11-20 下午9:51:57
	 */
	int createRole(RoleVO role);

	/**
	 * @Description 获取角色信息
	 * @param role
	 * @return
	 * @author bchen
	 * @created 2014-11-20 下午9:52:27
	 */
	RoleVO findRole(RoleVO role);

	/**
	 * @Description 修改角色信息
	 * @param role
	 * @return
	 * @author bchen
	 * @created 2014-11-20 下午9:52:54
	 */
	int updateRole(RoleVO role);

	/**
	 * @Description 删除角色
	 * @param role
	 * @author bchen
	 * @created 2014-11-20 下午9:54:12
	 */
	void deleteRole(RoleVO role);
	
	/**
	 * @Description 获取可申请的角色
	 * @return
	 * @author bchen
	 * @created 2016-10-23 下午9:24:57
	 */
	List<RoleVO> findApplyRoleList();

}
