package org.btsoft.jf.security.group.service;

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
import org.btsoft.jf.security.group.entity.GroupConstant;
import org.btsoft.jf.security.group.entity.GroupVO;
import org.btsoft.jf.security.relation.entity.RelationVO;

/**
 * @ClassName IGroupService
 * @Description 群组Service接口
 * @author bchen
 * @created 2013-11-19 11:22:10
 */
@Path("/group")
@Produces(MediaType.APPLICATION_JSON)
public interface IGroupService {

	/**
	 * @Description 分页查询群组信息
	 * @param group
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2013-12-10 10:17:22
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<GroupVO> findGroupList(@QueryParam("") GroupVO group,
			@QueryParam("") PageVO page) throws ApplicationException;

	/**
	 * @Description 创建单个群组
	 * @param group
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2015-8-28 下午9:36:31
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createGroup(GroupVO group) throws ApplicationException;

	/**
	 * @Description 更新单个群组
	 * @param group
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2015-8-28 下午9:36:31
	 */
	@PUT
	@Path(CoreConstant.PATH_UPDATE)
	public void updateGroup(GroupVO group) throws ApplicationException;

	/**
	 * @Description 删除单个群组
	 * @param group
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2015-9-6 下午11:17:04
	 */
	@DELETE
	@Path(CoreConstant.PATH_DELETE)
	public void deleteGroup(GroupVO group) throws ApplicationException;

	/**
	 * @Description 查看单个群组的信息
	 * @param group
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2015-9-6 下午11:18:24
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public GroupVO findGroup(@QueryParam("") GroupVO group)
			throws ApplicationException;

	/**
	 * @Description 分页查询群组用户信息
	 * @param groupUser
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2014-6-18 下午7:20:05
	 */
	@GET
	@Path(GroupConstant.GROUP_USER_LIST_PATH)
	public PagedResult<RelationVO> findGroupUserList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 删除群组和用户的关系
	 * @param groupUser
	 * @author bchen
	 * @created 2015-9-7 下午11:13:18
	 */
	@POST
	@Path(GroupConstant.GROUP_USER_DELETE_PATH)
	public void deleteGroupUser(RelationVO relation)
			throws ApplicationException;

	/**
	 * @Description 获取没有绑定某群组的用户列表
	 * @param user
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2015-9-7 下午11:17:32
	 */
	@GET
	@Path(GroupConstant.GROUP_USER_NO_BIND_PATH)
	public PagedResult<RelationVO> findNoGroupUserList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page);

	/**
	 * @Description 批量绑定用户和群组的关系
	 * @param groupUsers
	 * @author bchen
	 * @created 2015-9-7 下午11:28:30
	 */
	@POST
	@Path(GroupConstant.GROUP_USER_BIND_PATH)
	public void insertGroupUsers(List<RelationVO> relations);

	/**
	 * @Description 删除群组和角色的关系
	 * @param groupRole
	 * @author bchen
	 * @created 2015-9-7 下午11:13:18
	 */
	@POST
	@Path(GroupConstant.GROUP_ROLE_DELETE_PATH)
	public void deleteGroupRole(RelationVO relation)
			throws ApplicationException;

	/**
	 * @Description 获取没有绑定某群组的角色列表
	 * @param groupRole
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2015-9-7 下午11:17:32
	 */
	@GET
	@Path(GroupConstant.GROUP_ROLE_NO_BIND_PATH)
	public PagedResult<RelationVO> findNoGroupRoleList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page);

	/**
	 * @Description 批量绑定角色和群组的关系
	 * @param groupRoles
	 * @author bchen
	 * @created 2015-9-7 下午11:28:30
	 */
	@POST
	@Path(GroupConstant.GROUP_ROLE_BIND_PATH)
	public void insertGroupRoles(List<RelationVO> relations);

	/**
	 * @Description 分页查询群组角色信息
	 * @param groupRole
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2014-6-18 下午7:20:05
	 */
	@GET
	@Path(GroupConstant.GROUP_ROLE_LIST_PATH)
	public PagedResult<RelationVO> findGroupRoleList(
			@QueryParam("") RelationVO relation, @QueryParam("") PageVO page)
			throws ApplicationException;

}
