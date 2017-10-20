package org.btsoft.jf.config.lookup.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.lookup.dao.ILookupDao;
import org.btsoft.jf.config.lookup.entity.LookupConstant;
import org.btsoft.jf.config.lookup.entity.LookupVO;
import org.btsoft.jf.config.lookup.service.ILookupService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName LookupService
 * @Description Lookup service实现类
 * @author bchen
 * @created 2017年8月11日 下午9:45:30
 */
@Named
@JResource(code = LookupConstant.RESOURCE_CODE, descCN = LookupConstant.RESOURCE_DESC_CN, descEN = LookupConstant.RESOURCE_DESC_EN)
public class LookupService implements ILookupService, IRestService {

	@Inject
	private ILookupDao dao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = LookupConstant.LIST_DESC_CN, descEN = LookupConstant.LIST_DESC_EN)
	public PagedResult<LookupVO> findLookupList(LookupVO lookup, PageVO page) throws ApplicationException {
		return dao.findLookupList(lookup, page);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = LookupConstant.SINGLE_DESC_CN, descEN = LookupConstant.SINGLE_DESC_EN)
	public LookupVO findLookup(LookupVO lookup) throws ApplicationException {
		return dao.findLookup(lookup);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = LookupConstant.CREATE_DESC_CN, descEN = LookupConstant.CREATE_DESC_EN, isAudit = true)
	public void createLookup(LookupVO lookup) throws ApplicationException {
		dao.createLookup(lookup);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = LookupConstant.UPDATE_DESC_CN, descEN = LookupConstant.UPDATE_DESC_EN, isAudit = true)
	public void updateLookup(LookupVO lookup) throws ApplicationException {
		dao.updateLookup(lookup);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = LookupConstant.DELETE_DESC_CN, descEN = LookupConstant.DELETE_DESC_EN, isAudit = true)
	public void deleteLookup(LookupVO lookup) throws ApplicationException {
		dao.deleteLookup(lookup);
	}

}
