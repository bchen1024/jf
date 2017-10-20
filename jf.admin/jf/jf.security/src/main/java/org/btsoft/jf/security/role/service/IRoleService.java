package org.btsoft.jf.security.role.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.security.relation.entity.RelationVO;
import org.btsoft.jf.security.role.entity.RoleConstant;
import org.btsoft.jf.security.role.entity.RoleVO;

/**
 * @ClassName IRoleService
 * @Description 角色管理Service接口
 * @author bchen
 * @created 2013-11-5 9:21:41
 */

@Path("/role")
@Produces(MediaType.APPLICATION_JSON)
public interface IRoleService {

	/**
	 * @Description 分页查询角色信息
	 * @param role
	 * @param page
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2014-6-4 21:13:50
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<RoleVO> findRoleList(@QueryParam("") RoleVO role,
			@QueryParam("") PageVO page) throws ApplicationException;

	/**
	 * @Description 创建单个角色
	 * @param role
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2014-11-20 下午10:23:47
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createRole(RoleVO role) throws ApplicationException;

	/**
	 * @Description 获取单个角色信息
	 * @param role
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2014-11-20 下午10:24:49
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public RoleVO findRole(@QueryParam("") RoleVO role)
			throws ApplicationException;

	/**
	 * @Description 更新角色
	 * @param role
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2014-11-20 下午10:25:43
	 */
	@PUT
	@Path(CoreConstant.PATH_UPDATE)
	public void updateRole(RoleVO role) throws ApplicationException;

	/**
	 * @Description 删除角色
	 * @param role
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2014-11-22 下午9:56:00
	 */
	@DELETE
	@Path(CoreConstant.PATH_DELETE)
	public void deleteRole(RoleVO role) throws ApplicationException;

	/**
	 * @Description 获取拥有某个角色用户数据
	 * @param grantUser
	 * @param page
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2014-11-22 下午10:52:41
	 */
	@GET
	@Path(RoleConstant.ROLE_USER_LIST_PATH)
	public PagedResult<RelationVO> findRoleUserList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 获取没有授予某角色的用户列表
	 * @param userRole
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2015-9-10 上午12:31:13
	 */
	@GET
	@Path(RoleConstant.ROLE_USER_NO_BIND_PATH)
	public PagedResult<RelationVO> findNoRoleUserList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 绑定角色用户关系
	 * @param userRoles
	 * @author bchen
	 * @created 2015-9-10 上午12:40:43
	 */
	@POST
	@Path(RoleConstant.ROLE_USER_BIND_PATH)
	public void insertUserRoles(List<RelationVO> relations)
			throws ApplicationException;

	/**
	 * @Description 删除角色用户关系
	 * @param userRole
	 * @author bchen
	 * @created 2015-9-10 下午11:56:30
	 */
	@POST
	@Path(RoleConstant.ROLE_USER_DELETE_PATH)
	public void deleteUserRole(RelationVO relation) throws ApplicationException;

	/**
	 * @Description 获取某个角色属于的群组
	 * @param groupRole
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-4-6 下午11:39:20
	 */
	@GET
	@Path(RoleConstant.ROLE_GROUP_LIST_PATH)
	public PagedResult<RelationVO> findRoleGroupList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 获取某个角色还有拥有的群组
	 * @param groupRole
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-4-6 下午11:45:40
	 */
	@GET
	@Path(RoleConstant.ROLE_GROUP_NO_BIND_PATH)
	public PagedResult<RelationVO> findNoRoleGroupList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 绑定群组和角色的关系
	 * @param groupRoles
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-4-7 上午12:14:05
	 */
	@POST
	@Path(RoleConstant.ROLE_GROUP_BIND_PATH)
	public void insertGroupRoles(List<RelationVO> relations)
			throws ApplicationException;

	/**
	 * @Description 删除群组和角色的关系
	 * @param groupRoles
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-4-7 上午12:14:05
	 */
	@POST
	@Path(RoleConstant.ROLE_GROUP_DELETE_PATH)
	public void deleteGroupRole(RelationVO relation)
			throws ApplicationException;

	/**
	 * @Description 获取某个角色的权限点
	 * @param roleMenu
	 * @return
	 * @author bchen
	 * @created 2016-4-8 下午10:38:20
	 */
	@GET
	@Path(RoleConstant.ROLE_PERMISSION_TREE_PATH)
	public List<Map<String, Object>> findRolePermissionList(
			@QueryParam("") RelationVO relation) throws ApplicationException;

	/**
	 * @Description 保存角色权限点关系
	 * @param params
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-4-9 下午7:26:05
	 */
	@POST
	@Path(RoleConstant.ROLE_PERMISSION_CREATE_PATH)
	public void saveRolePermission(Map<String, Object> params)
			throws ApplicationException;

	/**
	 * @Description 获取可申请的角色
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-10-23 下午9:23:24
	 */
	@GET
	@Path("/find/applyRoles")
	public List<RoleVO> findApplyRoleList() throws ApplicationException;
}
