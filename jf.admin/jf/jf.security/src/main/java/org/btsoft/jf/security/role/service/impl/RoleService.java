package org.btsoft.jf.security.role.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.content.RequestContext;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.utils.CollectionUtils;
import org.btsoft.jf.core.utils.TreeUtils;
import org.btsoft.jf.security.relation.dao.IRelationDao;
import org.btsoft.jf.security.relation.entity.RelationVO;
import org.btsoft.jf.security.role.dao.IRoleDao;
import org.btsoft.jf.security.role.entity.RoleConstant;
import org.btsoft.jf.security.role.entity.RoleVO;
import org.btsoft.jf.security.role.service.IRoleService;

/**
 * @ClassName RoleService
 * @Description 角色管理Service实现类
 * @author bchen
 * @created 2013-11-6 21:01:42
 */
@Named
@JResource(code = RoleConstant.RESOURCE_CODE, descCN = RoleConstant.RESOURCE_DESC_CN, descEN = RoleConstant.RESOURCE_DESC_EN)
public class RoleService implements IRoleService,IRestService {

	@Inject
	private IRoleDao roleDao;

	@Inject
	private IRelationDao relationDao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = RoleConstant.LIST_DESC_CN, descEN = RoleConstant.LIST_DESC_EN)
	public PagedResult<RoleVO> findRoleList(RoleVO role, PageVO page)
			throws ApplicationException {
		return roleDao.findRoleList(role, page);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = RoleConstant.CREATE_DESC_CN, descEN = RoleConstant.CREATE_DESC_EN,isAudit=true)
	public void createRole(RoleVO role) throws ApplicationException {
		roleDao.createRole(role);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = RoleConstant.SINGLE_DESC_CN, descEN = RoleConstant.SINGLE_DESC_EN)
	public RoleVO findRole(RoleVO role) throws ApplicationException {
		return roleDao.findRole(role);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = RoleConstant.UPDATE_DESC_CN, descEN = RoleConstant.UPDATE_DESC_EN,isAudit=true)
	public void updateRole(RoleVO role) throws ApplicationException {
		roleDao.updateRole(role);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = RoleConstant.DELETE_DESC_CN, descEN = RoleConstant.DELETE_DESC_EN,isAudit=true)
	public void deleteRole(RoleVO role) throws ApplicationException {
		// 删除该角色下的用户
		RelationVO relation = new RelationVO();
		relation.setRelationType("user-role");
		relation.setRelationId(role.getRoleId());
		relationDao.deleteRelation(relation);

		// 删除该角色下的群组
		relation.setRelationType("group-role");
		relationDao.deleteRelation(relation);

		// 删除该角色下的权限点
		relation = new RelationVO();
		relation.setRelationType("role-permission");
		relation.setMasterId(role.getRoleId());
		relationDao.deleteRelation(relation);

		// 删除角色
		roleDao.deleteRole(role);
	}

	@Override
	@JOperator(code = RoleConstant.ROLE_USER_LIST, descCN = RoleConstant.ROLE_USER_LIST_DESC_CN, descEN = RoleConstant.ROLE_USER_LIST_DESC_EN)
	public PagedResult<RelationVO> findRoleUserList(RelationVO relation,
			PageVO page) throws ApplicationException {
		relation.setRelationType("user-role");
		return relationDao.findRelationList(relation, page);
	}

	@Override
	@JOperator(code = RoleConstant.ROLE_USER_LIST, descCN = RoleConstant.ROLE_USER_LIST_DESC_CN, descEN = RoleConstant.ROLE_USER_LIST_DESC_EN)
	public PagedResult<RelationVO> findNoRoleUserList(RelationVO relation,
			PageVO page) throws ApplicationException {
		relation.setRelationType("ru");
		return relationDao.findNoRelationList(relation, page);
	}

	@Override
	@JOperator(code = RoleConstant.ROLE_USER_LIST, descCN = RoleConstant.ROLE_USER_LIST_DESC_CN, descEN = RoleConstant.ROLE_USER_LIST_DESC_EN,isAudit=true)
	public void insertUserRoles(List<RelationVO> relations)
			throws ApplicationException {
		if (!CollectionUtils.isNullOrEmpty(relations)) {
			relationDao.createRelation(relations);
		}
	}

	@Override
	@JOperator(code = RoleConstant.ROLE_USER_LIST, descCN = RoleConstant.ROLE_USER_LIST_DESC_CN, descEN = RoleConstant.ROLE_USER_LIST_DESC_EN,isAudit=true)
	public void deleteUserRole(RelationVO relation) throws ApplicationException {
		relation.setRelationType("user-role");
		relationDao.deleteRelation(relation);
	}

	@Override
	@JOperator(code = RoleConstant.ROLE_GROUP_LIST, descCN = RoleConstant.ROLE_GROUP_LIST_DESC_CN, descEN = RoleConstant.ROLE_GROUP_LIST_DESC_EN)
	public PagedResult<RelationVO> findRoleGroupList(RelationVO relation,
			PageVO page) throws ApplicationException {
		relation.setRelationType("group-role");
		return relationDao.findRelationList(relation, page);
	}

	@Override
	@JOperator(code = RoleConstant.ROLE_GROUP_LIST, descCN = RoleConstant.ROLE_GROUP_LIST_DESC_CN, descEN = RoleConstant.ROLE_GROUP_LIST_DESC_EN)
	public PagedResult<RelationVO> findNoRoleGroupList(RelationVO relation,
			PageVO page) throws ApplicationException {
		relation.setRelationType("gr");
		return relationDao.findNoRelationList(relation, page);
	}

	@Override
	@JOperator(code = RoleConstant.ROLE_GROUP_LIST, descCN = RoleConstant.ROLE_GROUP_LIST_DESC_CN, descEN = RoleConstant.ROLE_GROUP_LIST_DESC_EN,isAudit=true)
	public void insertGroupRoles(List<RelationVO> relations)
			throws ApplicationException {
		if (!CollectionUtils.isNullOrEmpty(relations)) {
			relationDao.createRelation(relations);
		}
	}

	@Override
	@JOperator(code = RoleConstant.ROLE_GROUP_LIST, descCN = RoleConstant.ROLE_GROUP_LIST_DESC_CN, descEN = RoleConstant.ROLE_GROUP_LIST_DESC_EN,isAudit=true)
	public void deleteGroupRole(RelationVO relation)
			throws ApplicationException {
		relation.setRelationType("group-role");
		relationDao.deleteRelation(relation);
	}

	@Override
	@JOperator(code = RoleConstant.ROLE_PERMISSION_TREE, descCN = RoleConstant.ROLE_PERMISSION_TREE_DESC_CN, descEN = RoleConstant.ROLE_PERMISSION_TREE_DESC_EN)
	public List<Map<String, Object>> findRolePermissionList(RelationVO relation)
			throws ApplicationException {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", null);
		root.put("text", "All Permission");
		root.put("state", "open");
		List<Map<String, Object>> treeList=relationDao.findPermissionTreeByRole(relation);
		root.put("children",TreeUtils.listToTreeList(treeList, -1L,"id","parentId"));
		result.add(root);
		return result;
	}

	@Override
	@JOperator(code = RoleConstant.ROLE_PERMISSION_TREE, descCN = RoleConstant.ROLE_PERMISSION_TREE_DESC_CN, descEN = RoleConstant.ROLE_PERMISSION_TREE_DESC_EN,isAudit=true)
	public void saveRolePermission(Map<String, Object> params)
			throws ApplicationException {
		params.put("currentUserId", RequestContext.getCurrent().getUser()
				.getUserId());
		relationDao.createRolePermissionRef(params);
	}

	@Override
	public List<RoleVO> findApplyRoleList() throws ApplicationException {
		return roleDao.findApplyRoleList();
	}

}
