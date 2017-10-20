package org.btsoft.jf.security.group.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.utils.CollectionUtils;
import org.btsoft.jf.security.group.dao.IGroupDao;
import org.btsoft.jf.security.group.entity.GroupConstant;
import org.btsoft.jf.security.group.entity.GroupVO;
import org.btsoft.jf.security.group.service.IGroupService;
import org.btsoft.jf.security.relation.dao.IRelationDao;
import org.btsoft.jf.security.relation.entity.RelationVO;

/**
 * @ClassName GroupService
 * @Description 群组Service实现类
 * @author bchen
 * @created 2013-11-19 11:22:28
 */
@Named
@JResource(code = GroupConstant.RESOURCE_CODE, descCN = GroupConstant.RESOURCE_DESC_CN, descEN = GroupConstant.RESOURCE_DESC_EN)
public class GroupService implements IGroupService,IRestService {

	@Inject
	private IGroupDao groupDao;

	@Inject
	private IRelationDao relationDao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = GroupConstant.LIST_DESC_CN, descEN = GroupConstant.LIST_DESC_EN)
	public PagedResult<GroupVO> findGroupList(GroupVO group, PageVO page)
			throws ApplicationException {
		return groupDao.findGroupList(group, page);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = GroupConstant.CREATE_DESC_CN, descEN = GroupConstant.CREATE_DESC_EN,isAudit=true)
	public void createGroup(GroupVO group) throws ApplicationException {
		groupDao.createGroup(group);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = GroupConstant.UPDATE_DESC_CN, descEN = GroupConstant.UPDATE_DESC_EN,isAudit=true)
	public void updateGroup(GroupVO group) throws ApplicationException {
		groupDao.updateGroup(group);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = GroupConstant.DELETE_DESC_CN, descEN = GroupConstant.DELETE_DESC_EN,isAudit=true)
	public void deleteGroup(GroupVO group) throws ApplicationException {
		//删除群组用户
		RelationVO relation=new RelationVO();
		relation.setRelationType("group-user");
		relation.setMasterId(group.getGroupId());
		relationDao.deleteRelation(relation);
		
		//删除群组角色
		relation.setRelationType("group-role");
		relationDao.deleteRelation(relation);
		
		//删除群组
		groupDao.deleteGroup(group);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = GroupConstant.SINGLE_DESC_CN, descEN = GroupConstant.SINGLE_DESC_EN)
	public GroupVO findGroup(GroupVO group) throws ApplicationException {
		return groupDao.findGroup(group);
	}

	@Override
	@JOperator(code = GroupConstant.GROUP_USER_LIST, descCN = GroupConstant.GROUP_USER_LIST_DESC_CN, descEN = GroupConstant.GROUP_USER_LIST_DESC_EN)
	public PagedResult<RelationVO> findGroupUserList(RelationVO relation,
			PageVO page) {
		relation.setRelationType("group-user");
		return relationDao.findRelationList(relation, page);
	}

	@Override
	@JOperator(code = GroupConstant.GROUP_USER_LIST, descCN = GroupConstant.GROUP_USER_LIST_DESC_CN, descEN = GroupConstant.GROUP_USER_LIST_DESC_EN,isAudit=true)
	public void deleteGroupUser(RelationVO relation)
			throws ApplicationException {
		relation.setRelationType("group-user");
		relationDao.deleteRelation(relation);
	}

	@Override
	@JOperator(code = GroupConstant.GROUP_USER_LIST, descCN = GroupConstant.GROUP_USER_LIST_DESC_CN, descEN = GroupConstant.GROUP_USER_LIST_DESC_EN)
	public PagedResult<RelationVO> findNoGroupUserList(RelationVO relation,
			PageVO page) {
		relation.setRelationType("gu");
		return relationDao.findNoRelationList(relation, page);
	}

	@Override
	@JOperator(code = GroupConstant.GROUP_USER_LIST, descCN = GroupConstant.GROUP_USER_LIST_DESC_CN, descEN = GroupConstant.GROUP_USER_LIST_DESC_EN,isAudit=true)
	public void insertGroupUsers(List<RelationVO> relations) {
		if (!CollectionUtils.isNullOrEmpty(relations)) {
			relationDao.createRelation(relations);
		}

	}

	@Override
	@JOperator(code = GroupConstant.GROUP_ROLE_LIST, descCN = GroupConstant.GROUP_ROLE_LIST_DESC_CN, descEN = GroupConstant.GROUP_ROLE_LIST_DESC_EN,isAudit=true)
	public void deleteGroupRole(RelationVO relation)
			throws ApplicationException {
		relation.setRelationType("group-role");
		relationDao.deleteRelation(relation);
	}

	@Override
	@JOperator(code = GroupConstant.GROUP_ROLE_LIST, descCN = GroupConstant.GROUP_ROLE_LIST_DESC_CN, descEN = GroupConstant.GROUP_ROLE_LIST_DESC_EN)
	public PagedResult<RelationVO> findNoGroupRoleList(RelationVO relation,
			PageVO page) {
		relation.setRelationType("gr");
		return relationDao.findNoRelationList(relation, page);
	}

	@Override
	@JOperator(code = GroupConstant.GROUP_ROLE_LIST, descCN = GroupConstant.GROUP_ROLE_LIST_DESC_CN, descEN = GroupConstant.GROUP_ROLE_LIST_DESC_EN,isAudit=true)
	public void insertGroupRoles(List<RelationVO> relations) {
		if (!CollectionUtils.isNullOrEmpty(relations)) {
			relationDao.createRelation(relations);
		}
	}

	@Override
	@JOperator(code = GroupConstant.GROUP_ROLE_LIST, descCN = GroupConstant.GROUP_ROLE_LIST_DESC_CN, descEN = GroupConstant.GROUP_ROLE_LIST_DESC_EN)
	public PagedResult<RelationVO> findGroupRoleList(RelationVO relation,
			PageVO page) throws ApplicationException {
		relation.setRelationType("group-role");
		return relationDao.findRelationList(relation, page);
	}

}
