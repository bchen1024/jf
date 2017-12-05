package org.btsoft.jf.security.user.service.impl;

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
import org.btsoft.jf.core.utils.DESEncrypt;
import org.btsoft.jf.security.relation.dao.IRelationDao;
import org.btsoft.jf.security.relation.entity.RelationVO;
import org.btsoft.jf.security.user.dao.IUserDao;
import org.btsoft.jf.security.user.entity.UserConstant;
import org.btsoft.jf.security.user.entity.UserVO;
import org.btsoft.jf.security.user.service.IUserService;

/**
 * @ClassName UserService
 * @Description 用户信息管理Service实现类
 * @author bchen
 * @created 2013-11-20 12:01:26
 */
@Named
@JResource(code = UserConstant.RESOURCE_CODE, descCN = UserConstant.RESOURCE_DESC_CN, descEN = UserConstant.RESOURCE_DESC_EN)
public class UserService implements IUserService, IRestService {

	@Inject
	private IUserDao userDao;

	@Inject
	private IRelationDao relationDao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = UserConstant.LIST_DESC_CN, descEN = UserConstant.LIST_DESC_EN)
	public PagedResult<UserVO> findUserList(UserVO user, PageVO page) throws ApplicationException {
		return userDao.findUserList(user, page);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = UserConstant.SINGLE_DESC_CN, descEN = UserConstant.SINGLE_DESC_EN)
	public UserVO findUser(UserVO user) throws ApplicationException {
		return userDao.findUser(user);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = UserConstant.CREATE_DESC_CN, descEN = UserConstant.CREATE_DESC_EN, isAudit = true)
	public UserVO createUser(UserVO user) throws ApplicationException {

		// 获取初始化密码
		String userPassword = "test1234";

		// 设置密码并加密
		user.setUserPassword(DESEncrypt.encrypt(userPassword));

		// 创建用户
		userDao.createUser(user);

		return user;
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = UserConstant.UPDATE_DESC_CN, descEN = UserConstant.UPDATE_DESC_EN, isAudit = true)
	public void updateUser(UserVO user) throws ApplicationException {
		userDao.updateUser(user);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = UserConstant.DELETE_DESC_CN, descEN = UserConstant.DELETE_DESC_EN, isAudit = true)
	public void deleteUser(UserVO user) throws ApplicationException {
		// 删除该用户下的角色
		RelationVO relation = new RelationVO();
		relation.setRelationType("user-role");
		relation.setMasterId(user.getUserId());
		relationDao.deleteRelation(relation);

		// 删除该用户下的群组
		relation = new RelationVO();
		relation.setRelationType("group-user");
		relation.setRelationId(user.getUserId());
		relationDao.deleteRelation(relation);

		// 删除用户
		userDao.deleteUser(user);
	}

	@Override
	@JOperator(code = UserConstant.USER_ROLE_LIST, descCN = UserConstant.USER_ROLE_LIST_DESC_CN, descEN = UserConstant.USER_ROLE_LIST_DESC_EN)
	public PagedResult<RelationVO> findUserRoleList(RelationVO relation, PageVO page) throws ApplicationException {
		relation.setRelationType("user-role");
		return relationDao.findRelationList(relation, page);
	}

	@Override
	public PagedResult<RelationVO> findMyRoleList(RelationVO relation, PageVO page) throws ApplicationException {
		relation.setRelationType("user-role");
		return relationDao.findRelationList(relation, page);
	}

	@Override
	@JOperator(code = UserConstant.USER_ROLE_LIST, descCN = UserConstant.USER_ROLE_LIST_DESC_CN, descEN = UserConstant.USER_ROLE_LIST_DESC_EN)
	public PagedResult<RelationVO> findNoUserRoleList(RelationVO relation, PageVO page) throws ApplicationException {
		relation.setRelationType("ur");
		return relationDao.findNoRelationList(relation, page);
	}

	@Override
	@JOperator(code = UserConstant.USER_ROLE_LIST, descCN = UserConstant.USER_ROLE_LIST_DESC_CN, descEN = UserConstant.USER_ROLE_LIST_DESC_EN, isAudit = true)
	public void insertUserRoles(List<RelationVO> relations) throws ApplicationException {
		if (!CollectionUtils.isNullOrEmpty(relations)) {
			relationDao.createRelation(relations);
		}
	}

	@Override
	@JOperator(code = UserConstant.USER_ROLE_LIST, descCN = UserConstant.USER_ROLE_LIST_DESC_CN, descEN = UserConstant.USER_ROLE_LIST_DESC_EN, isAudit = true)
	public void deleteUserRole(RelationVO relation) throws ApplicationException {
		relation.setRelationType("user-role");
		relationDao.deleteRelation(relation);
	}

	@Override
	@JOperator(code = UserConstant.USER_GROUP_LIST, descCN = UserConstant.USER_GROUP_LIST_DESC_CN, descEN = UserConstant.USER_GROUP_LIST_DESC_EN)
	public PagedResult<RelationVO> findUserGroupList(RelationVO relation, PageVO page) throws ApplicationException {
		relation.setRelationType("group-user");
		return relationDao.findRelationList(relation, page);
	}

	@Override
	public PagedResult<RelationVO> findMyGroupList(RelationVO relation, PageVO page) throws ApplicationException {
		relation.setRelationType("group-user");
		return relationDao.findRelationList(relation, page);
	}

	@Override
	@JOperator(code = UserConstant.USER_GROUP_LIST, descCN = UserConstant.USER_GROUP_LIST_DESC_CN, descEN = UserConstant.USER_GROUP_LIST_DESC_EN)
	public PagedResult<RelationVO> findNoUserGroupList(RelationVO relation, PageVO page) throws ApplicationException {
		relation.setRelationType("ug");
		return relationDao.findNoRelationList(relation, page);
	}

	@Override
	@JOperator(code = UserConstant.USER_GROUP_LIST, descCN = UserConstant.USER_GROUP_LIST_DESC_CN, descEN = UserConstant.USER_GROUP_LIST_DESC_EN, isAudit = true)
	public void insertUserGroups(List<RelationVO> relations) throws ApplicationException {
		if (!CollectionUtils.isNullOrEmpty(relations)) {
			relationDao.createRelation(relations);
		}
	}

	@Override
	@JOperator(code = UserConstant.USER_GROUP_LIST, descCN = UserConstant.USER_GROUP_LIST_DESC_CN, descEN = UserConstant.USER_GROUP_LIST_DESC_EN, isAudit = true)
	public void deleteUserGroup(RelationVO relation) throws ApplicationException {
		relation.setRelationType("group-user");
		relationDao.deleteRelation(relation);
	}

	@Override
	public List<UserVO> queryUser(UserVO user) throws ApplicationException {
		return userDao.queryUser(user);
	}
}
