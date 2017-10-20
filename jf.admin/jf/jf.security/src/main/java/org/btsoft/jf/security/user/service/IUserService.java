package org.btsoft.jf.security.user.service;

import java.util.List;

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
import org.btsoft.jf.security.user.entity.UserConstant;
import org.btsoft.jf.security.user.entity.UserVO;

/**
 * @ClassName IUserService
 * @Description 用户管理Service接口
 * @author bchen
 * @created 2013-11-20 12:01:08
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public interface IUserService {

	/**
	 * @Description 登录系统
	 * @param user
	 * @return
	 * @author bchen
	 * @created 2015-2-3 下午11:28:41
	 */
	public UserVO loginSystem(UserVO user) throws ApplicationException;

	/**
	 * @Description 分页查询用户信息
	 * @param user
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2013-12-20 6:49:04
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<UserVO> findUserList(@QueryParam("") UserVO user,
			@QueryParam("") PageVO page) throws ApplicationException;

	/**
	 * @Description 获取单个用户信息
	 * @param user
	 * @return
	 * @author bchen
	 * @created 2013-12-8 10:46:47
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public UserVO findUser(@QueryParam("") UserVO user)
			throws ApplicationException;

	/**
	 * @Description 创建用户
	 * @param user
	 * @return
	 * @author bchen
	 * @created 2013-12-9 7:18:01
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public UserVO createUser(UserVO user) throws ApplicationException;

	/**
	 * @Description 更新用户信息
	 * @param user
	 * @author bchen
	 * @created 2013-12-9 7:23:23
	 */
	@PUT
	@Path(CoreConstant.PATH_UPDATE)
	public void updateUser(UserVO user) throws ApplicationException;

	/**
	 * @Description
	 * @param user
	 * @author bchen
	 * @created 2013-12-9 7:35:38
	 */
	@DELETE
	@Path(CoreConstant.PATH_DELETE)
	public void deleteUser(UserVO user) throws ApplicationException;

	/**
	 * @Description 获取某个用户拥有的角色
	 * @param userRole
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2014-11-24 下午9:45:34
	 */
	@GET
	@Path(UserConstant.USER_ROLE_LIST_PATH)
	public PagedResult<RelationVO> findUserRoleList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 获取某个用户拥有的角色
	 * @param userRole
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2014-11-24 下午9:45:34
	 */
	@GET
	@Path(UserConstant.MY_ROLE_LIST_PATH)
	public PagedResult<RelationVO> findMyRoleList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 获取某个用户没有拥有的角色
	 * @param userRole
	 * @return
	 * @author bchen
	 * @created 2016-3-19 下午4:10:50
	 */
	@GET
	@Path(UserConstant.USER_ROLE_NO_BIND_PATH)
	public PagedResult<RelationVO> findNoUserRoleList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 绑定用户角色关系
	 * @param userRoles
	 * @author bchen
	 * @created 2016-4-9 下午11:04:01
	 */
	@POST
	@Path(UserConstant.USER_ROLE_BIND_PATH)
	public void insertUserRoles(List<RelationVO> relations)
			throws ApplicationException;

	/**
	 * @Description 删除用户角色关系
	 * @param userRole
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-4-9 下午11:08:56
	 */
	@POST
	@Path(UserConstant.USER_ROLE_DELETE_PATH)
	public void deleteUserRole(RelationVO relation) throws ApplicationException;

	/**
	 * @Description 获取某个用户拥有的群组
	 * @param param
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-3-20 下午11:15:02
	 */
	@GET
	@Path(UserConstant.USER_GROUP_LIST_PATH)
	public PagedResult<RelationVO> findUserGroupList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 获取某个用户拥有的群组
	 * @param param
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-3-20 下午11:15:02
	 */
	@GET
	@Path(UserConstant.MY_GROUP_LIST_PATH)
	public PagedResult<RelationVO> findMyGroupList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 获取某个用户未拥有的群组
	 * @param param
	 * @param page
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-3-20 下午11:30:53
	 */
	@GET
	@Path(UserConstant.USER_GROUP_NO_BIND_PATH)
	public PagedResult<RelationVO> findNoUserGroupList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 绑定用户群组的关系
	 * @param groupUsers
	 * @author bchen
	 * @created 2016-4-9 下午11:39:50
	 */
	@POST
	@Path(UserConstant.USER_GROUP_BIND_PATH)
	public void insertUserGroups(List<RelationVO> relations)
			throws ApplicationException;

	/**
	 * @Description 删除用户群组关系
	 * @param groupUser
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-4-9 下午11:40:46
	 */
	@POST
	@Path(UserConstant.USER_GROUP_DELETE_PATH)
	public void deleteUserGroup(RelationVO relation)
			throws ApplicationException;

	/**
	 * @Description 查询用户
	 * @param user
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-10-22 下午11:53:51
	 */
	@GET
	@Path("/find/queryUser")
	public List<UserVO> queryUser(@QueryParam("")UserVO user)
			throws ApplicationException;
}
