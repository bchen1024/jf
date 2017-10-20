package org.btsoft.jf.security.permission.dao;

import java.util.List;
import java.util.Map;

import org.btsoft.jf.security.permission.entity.PermissionVO;

/**
 * @ClassName IPermissionDao
 * @Description 权限点Dao接口
 * @author bchen
 * @created 2013-11-19 23:41:54
 */
public interface IPermissionDao {

	/**
	 * @Description 获取所有的权限点,生成权限Grid树
	 * @param permission
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2014-1-12 9:42:24
	 */
	List<PermissionVO> findPermissionList(PermissionVO permission);

	/**
	 * @Description 创建权限点
	 * @param permission
	 * @author bchen
	 * @created 2014-4-13 6:31:56
	 */
	int createPermission(PermissionVO permission);

	/**
	 * @Description 获取单个权限点信息
	 * @param permission
	 * @return
	 * @author bchen
	 * @created 2014-4-13 7:05:36
	 */
	PermissionVO findPermission(PermissionVO permission);

	/**
	 * @Description 更新单个权限点信息
	 * @param permission
	 * @return
	 * @author bchen
	 * @created 2014-4-13 7:18:08
	 */
	int updatePermission(PermissionVO permission);

	/**
	 * @Description 删除单个权限点信息
	 * @param permission
	 * @return
	 * @author bchen
	 * @created 2014-5-1 10:19:26
	 */
	int deletePermission(PermissionVO permission);
	
	/**
	 * @Description 批量保存权限点
	 * @param permissionList
	 * @author bchen
	 * @created 2016-4-10 下午10:03:13
	 */
	void batchSavePermission(List<PermissionVO> permissionList);
	
	/**
	 * @Description 获取某些权限编码的权限id
	 * @param permissionList
	 * @return
	 * @author bchen
	 * @created 2016-4-10 下午11:03:49
	 */
	List<Map<String,Object>> findPermissionIdByCode(List<PermissionVO> permissionList);
	
	/**
	 * @Description 失效某些注解被删除但存在于数据库的权限点
	 * @param params
	 * @return
	 * @author bchen
	 * @created 2016-9-27 下午11:00:29
	 */
	int inValidPermission(Map<String,Object> params);
	
	/**
	 * @Description 物理删除失效的权限点
	 * @param permission
	 * @return
	 * @author bchen
	 * @created 2016-9-28 上午12:13:08
	 */
	void deleteInValidPermission(PermissionVO permission);
	
}
