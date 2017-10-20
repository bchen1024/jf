package org.btsoft.jf.security.relation.dao;

import java.util.List;
import java.util.Map;

import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.security.relation.entity.RelationVO;

/**
 * @ClassName IRelationDao
 * @Description 数据关系Dao接口
 * @author bchen
 * @created 2016-9-23 下午10:42:27
 */
public interface IRelationDao {

	/**
	 * @Description 创建关系
	 * @param relations
	 * @return
	 * @author bchen
	 * @created 2016-9-23 下午11:29:16
	 */
	public void createRelation(List<RelationVO> relations);

	/**
	 * @Description 删除关系
	 * @param relation
	 * @author bchen
	 * @created 2016-9-24 上午12:00:25
	 */
	public int deleteRelation(RelationVO relation);

	/**
	 * @Description 分页获取关系数据
	 * @param relation
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-9-24 上午12:13:00
	 */
	public PagedResult<RelationVO> findRelationList(RelationVO relation,
			PageVO page);

	/**
	 * @Description 分页获取没有关系数据
	 * @param relation
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-9-24 上午12:13:00
	 */
	public PagedResult<RelationVO> findNoRelationList(RelationVO relation,
			PageVO page);

	/**
	 * @Description 获取某个用户拥有的角色
	 * @param relation
	 * @return
	 * @author bchen
	 * @created 2016-9-25 上午12:31:11
	 */
	public List<RelationVO> findRoleListByUser(RelationVO relation);

	/**
	 * @Description 获取某个用户可查看的菜单
	 * @param params
	 * @return
	 * @author bchen
	 * @created 2016-9-25 下午12:13:22
	 */
	public List<Map<String, Object>> findMenuListByUser(
			Map<String, Object> params);

	/**
	 * @Description 获取某个用户拥有的权限
	 * @param params
	 * @return
	 * @author bchen
	 * @created 2016-9-25 下午5:25:06
	 */
	public List<String> findPermissionListByUser(Map<String, Object> params);

	/**
	 * @Description 获取某个角色拥有的权限点
	 * @param relation
	 * @return
	 * @author bchen
	 * @created 2016-9-25 下午10:05:00
	 */
	public List<Map<String,Object>> findPermissionTreeByRole(RelationVO relation);
	
	/**
	 * @Description 创建角色和权限点的关系
	 * @param params
	 * @author bchen
	 * @created 2016-9-25 下午10:19:23
	 */
	public void createRolePermissionRef(Map<String,Object> params);
}
