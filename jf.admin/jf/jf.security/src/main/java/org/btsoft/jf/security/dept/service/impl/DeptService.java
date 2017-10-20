package org.btsoft.jf.security.dept.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.security.dept.dao.IDeptDao;
import org.btsoft.jf.security.dept.entity.DeptConstant;
import org.btsoft.jf.security.dept.entity.DeptVO;
import org.btsoft.jf.security.dept.service.IDeptService;

/**
 * @ClassName DeptService
 * @Description 组织机构Service实现类
 * @author bchen
 * @created 2016-3-28 下午7:19:44
 */
@Named
@JResource(code = DeptConstant.RESOURCE_CODE, descCN = DeptConstant.RESOURCE_DESC_CN, descEN = DeptConstant.RESOURCE_DESC_EN)
public class DeptService implements IDeptService,IRestService {

	@Inject
	private IDeptDao deptDao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = DeptConstant.LIST_DESC_CN, descEN = DeptConstant.DELETE_DESC_EN)
	public PagedResult<DeptVO> findDeptList(DeptVO dept)
			throws ApplicationException {
		PagedResult<DeptVO> result = new PagedResult<DeptVO>();
		List<DeptVO> list = deptDao.findDeptList(dept);
		result.setResult(list);
		result.setTotal(list.size());
		return result;
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = DeptConstant.CREATE_DESC_CN, descEN = DeptConstant.CREATE_DESC_EN,isAudit=true)
	public void createDept(DeptVO dept) throws ApplicationException {
		deptDao.createDept(dept);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = DeptConstant.SINGLE_DESC_CN, descEN = DeptConstant.SINGLE_DESC_EN)
	public DeptVO findDept(DeptVO dept) throws ApplicationException {
		return deptDao.findDept(dept);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = DeptConstant.UPDATE_DESC_CN, descEN = DeptConstant.UPDATE_DESC_EN,isAudit=true)
	public void updateDept(DeptVO dept) throws ApplicationException {
		deptDao.updateDept(dept);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = DeptConstant.DELETE_DESC_CN, descEN = DeptConstant.DELETE_DESC_EN,isAudit=true)
	public void deleteDept(DeptVO dept) throws ApplicationException {
		deptDao.deleteDept(dept);
	}

}
