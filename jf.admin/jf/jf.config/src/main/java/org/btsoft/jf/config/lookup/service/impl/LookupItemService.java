package org.btsoft.jf.config.lookup.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.lookup.dao.ILookupItemDao;
import org.btsoft.jf.config.lookup.entity.LookupItemConstant;
import org.btsoft.jf.config.lookup.entity.LookupItemVO;
import org.btsoft.jf.config.lookup.service.ILookupItemService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName LookupItemService
 * @Description LookupItem service实现类
 * @author bchen
 * @created 2017年8月11日 下午9:59:15
 */
@Named
@JResource(code=LookupItemConstant.RESOURCE_CODE,descCN=LookupItemConstant.RESOURCE_DESC_CN,descEN=LookupItemConstant.RESOURCE_DESC_EN)
public class LookupItemService implements ILookupItemService, IRestService {

	@Inject
	private ILookupItemDao dao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = LookupItemConstant.LIST_DESC_CN, descEN = LookupItemConstant.LIST_DESC_EN)
	public PagedResult<LookupItemVO> findLookupItemList(LookupItemVO lookupItem, PageVO page)
			throws ApplicationException {
		return dao.findLookupItemList(lookupItem, page);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = LookupItemConstant.SINGLE_DESC_CN, descEN = LookupItemConstant.SINGLE_DESC_EN)
	public LookupItemVO findLookupItem(LookupItemVO lookupItem) throws ApplicationException {
		return dao.findLookupItem(lookupItem);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = LookupItemConstant.CREATE_DESC_CN, descEN = LookupItemConstant.CREATE_DESC_EN, isAudit = true)
	public void createLookupItem(LookupItemVO lookupItem) throws ApplicationException {
		dao.createLookupItem(lookupItem);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = LookupItemConstant.UPDATE_DESC_CN, descEN = LookupItemConstant.UPDATE_DESC_EN, isAudit = true)
	public void updateLookupItem(LookupItemVO lookupItem) throws ApplicationException {
		dao.updateLookupItem(lookupItem);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = LookupItemConstant.DELETE_DESC_CN, descEN = LookupItemConstant.DELETE_DESC_EN, isAudit = true)
	public void deleteLookupItem(LookupItemVO lookupItem) throws ApplicationException {
		dao.deleteLookupItem(lookupItem);
	}

	@Override
	public List<LookupItemVO> findLookupItemByCodeNo(LookupItemVO lookupItem) throws ApplicationException {
		return dao.findLookupItemByCodeNo(lookupItem);
	}

}
