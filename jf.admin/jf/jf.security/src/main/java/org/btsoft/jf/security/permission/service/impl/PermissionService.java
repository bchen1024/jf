package org.btsoft.jf.security.permission.service.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.utils.CollectionUtils;
import org.btsoft.jf.core.utils.StringUtils;
import org.btsoft.jf.security.permission.dao.IPermissionDao;
import org.btsoft.jf.security.permission.entity.PermissionConstant;
import org.btsoft.jf.security.permission.entity.PermissionVO;
import org.btsoft.jf.security.permission.service.IPermissionService;
import org.btsoft.jf.security.relation.dao.IRelationDao;
import org.btsoft.jf.security.relation.entity.RelationVO;
import org.springframework.aop.support.AopUtils;

/**
 * @ClassName PermissionService
 * @Description 权限点Service实现类
 * @author bchen
 * @created 2013-11-19 11:49:14
 */
@Named
@JResource(code = PermissionConstant.RESOURCE_CODE, descCN = PermissionConstant.RESOURCE_DESC_CN, descEN = PermissionConstant.RESOURCE_DESC_EN)
public class PermissionService implements IPermissionService, IRestService {

	@Inject
	private IPermissionDao permissionDao;

	@Inject
	private IRelationDao relationDao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = PermissionConstant.LIST_DESC_CN, descEN = PermissionConstant.LIST_DESC_EN)
	public PagedResult<PermissionVO> findPermissionList(PermissionVO permission) throws ApplicationException {
		PagedResult<PermissionVO> result = new PagedResult<PermissionVO>();
		List<PermissionVO> list = permissionDao.findPermissionList(permission);
		result.setResult(list);
		result.setTotal(list.size());
		return result;
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = PermissionConstant.CREATE_DESC_CN, descEN = PermissionConstant.CREATE_DESC_EN, isAudit = true)
	public void createPermission(PermissionVO permission) throws ApplicationException {
		permissionDao.createPermission(permission);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = PermissionConstant.SINGLE_DESC_CN, descEN = PermissionConstant.SINGLE_DESC_EN)
	public PermissionVO findPermission(PermissionVO permission) throws ApplicationException {
		return permissionDao.findPermission(permission);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = PermissionConstant.UPDATE_DESC_CN, descEN = PermissionConstant.UPDATE_DESC_EN, isAudit = true)
	public void updatePermission(PermissionVO permission) throws ApplicationException {
		permissionDao.updatePermission(permission);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = PermissionConstant.DELETE_DESC_CN, descEN = PermissionConstant.DELETE_DESC_EN, isAudit = true)
	public void deletePermission(PermissionVO permission) throws ApplicationException {

		// 删除该权限点的关系数据
		RelationVO relation = new RelationVO();
		relation.setRelationType("role-permission");
		//relation.setRelationId(permission.getPermissionId());
		relationDao.deleteRelation(relation);

		// 删除权限点
		permissionDao.deletePermission(permission);
	}

	@Override
	@JOperator(code = PermissionConstant.SYNC, descCN = PermissionConstant.SYNC_DESC_CN, descEN = PermissionConstant.SYNC_DESC_EN, isAudit = true)
	public Map<String, Object> syncPermission(PermissionVO permission) throws ApplicationException {
		Map<String, Object> result = new HashMap<String, Object>();
		// 获取所有有JResource注解的bean
		Map<String, Object> beans = JF.getContext().getBeansWithAnnotation(JResource.class);
		if (beans != null) {
			Map<String, PermissionVO> resourceList = new HashMap<String, PermissionVO>();
			Map<String, PermissionVO> methodList = new HashMap<String, PermissionVO>();
			for (Iterator<Object> iterator = beans.values().iterator(); iterator.hasNext();) {
				Class<?> clazz = AopUtils.getTargetClass(iterator.next());
				if (!clazz.isAnnotationPresent(JResource.class)) {
					continue;
				}

				// 获取JResource相关配置
				JResource jResource = clazz.getAnnotation(JResource.class);
				String resourceCode = jResource.code();
				if (!StringUtils.isEmpty(resourceCode) && !resourceList.containsKey(resourceCode)) {
					PermissionVO permissionVO = new PermissionVO();
					permissionVO.setPermissionCode(resourceCode);
					permissionVO.setPermissionDescCN(jResource.descCN());
					permissionVO.setPermissionDescEN(jResource.descEN());
					permissionVO.setPermissionType("resource");
					resourceList.put(resourceCode, permissionVO);
				}
				
				//获取方法级权限配置
				Method[] methods = clazz.getMethods();
				for (Method method : methods) {
					if (method.isAnnotationPresent(JOperator.class)) {
						JOperator operator = method.getAnnotation(JOperator.class);
						String methodCode = operator.code();
						if (!StringUtils.isEmpty(methodCode) && !methodList.containsKey(resourceCode+methodCode)) {
							String descCN = operator.descCN();
							String descEN = operator.descEN();
							PermissionVO permis = new PermissionVO();
							permis.setPermissionCode(methodCode);
							permis.setPermissionDescCN(descCN);
							permis.setPermissionDescEN(descEN);
							permis.setPermissionType("method");
							permis.setParentCode(resourceCode);
							methodList.put(resourceCode+methodCode, permis);
						}
					}
				}
			}
			List<PermissionVO> permissionList = new ArrayList<PermissionVO>();
			for (Iterator<PermissionVO> iterator = resourceList.values().iterator(); iterator.hasNext();) {
				permissionList.add(iterator.next());
			}
			for (Iterator<PermissionVO> iterator = methodList.values().iterator(); iterator.hasNext();) {
				permissionList.add(iterator.next());
			}
			if (!CollectionUtils.isNullOrEmpty(permissionList)) {
				permissionDao.batchSavePermission(permissionList);
				permissionList.clear();
			}
		}
		return result;
	}

	@Override
	@JOperator(descCN = PermissionConstant.RESOURCES_DESC_CN, descEN = PermissionConstant.RESOURCES_DESC_EN)
	public List<PermissionVO> findPermissionResource(PermissionVO permission) throws ApplicationException {
		return permissionDao.findPermissionList(permission);
	}

	@Override
	@JOperator(code = PermissionConstant.DEL_INVALID, descCN = PermissionConstant.DEL_INVALID_DESC_CN, descEN = PermissionConstant.DEL_INVALID_DESC_EN, isAudit = true)
	public void deleteInValidPermission(PermissionVO permission) {
		permissionDao.deleteInValidPermission(permission);
	}
}
