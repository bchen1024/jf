package org.btsoft.jf.config.i18n.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.i18n.dao.II18nDao;
import org.btsoft.jf.config.i18n.entity.I18nConstant;
import org.btsoft.jf.config.i18n.entity.I18nVO;
import org.btsoft.jf.config.i18n.service.II18nService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.cache.JFCache;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

@Named
@JResource(code = I18nConstant.RESOURCE_CODE, descCN = I18nConstant.RESOURCE_DESC_CN, descEN = I18nConstant.RESOURCE_DESC_EN)
public class I18nService implements II18nService, IRestService {

	@Inject
	private II18nDao i18nDao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = I18nConstant.LIST_DESC_CN, descEN = I18nConstant.LIST_DESC_EN)
	public PagedResult<I18nVO> findI18nList(I18nVO i18n, PageVO page) throws ApplicationException {
		return i18nDao.findI18nList(i18n, page);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = I18nConstant.SINGLE_DESC_CN, descEN = I18nConstant.SINGLE_DESC_EN)
	public I18nVO findI18n(I18nVO i18n) throws ApplicationException {
		return i18nDao.findI18n(i18n);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = I18nConstant.CREATE_DESC_CN, descEN = I18nConstant.CREATE_DESC_EN, isAudit = true)
	public void createI18n(I18nVO i18n) throws ApplicationException {
		i18nDao.createI18n(i18n);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = I18nConstant.UPDATE_DESC_CN, descEN = I18nConstant.UPDATE_DESC_EN, isAudit = true)
	public void updateI18n(I18nVO i18n) throws ApplicationException {
		i18nDao.updateI18n(i18n);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = I18nConstant.DELETE_DESC_CN, descEN = I18nConstant.DELETE_DESC_EN, isAudit = true)
	public void deleteI18n(I18nVO i18n) throws ApplicationException {
		i18nDao.deleteI18n(i18n);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CLEAR_CACHE, descCN = I18nConstant.CLEAR_CACHE_DESC_CN, descEN = I18nConstant.CLEAR_CACHE_DESC_EN, isAudit = true)
	public void clearCache() throws ApplicationException {
		JFCache.clearCache("i18n");
	}

	@Override
	public List<Map<String,String>> findI18ns(I18nVO i18n) throws ApplicationException {
		return i18nDao.findI18ns(i18n);
	}

}
