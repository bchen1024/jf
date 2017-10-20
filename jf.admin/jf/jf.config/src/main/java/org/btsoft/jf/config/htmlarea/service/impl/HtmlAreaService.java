package org.btsoft.jf.config.htmlarea.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.htmlarea.dao.IHtmlAreaDao;
import org.btsoft.jf.config.htmlarea.entity.HtmlAreaConstant;
import org.btsoft.jf.config.htmlarea.entity.HtmlAreaVO;
import org.btsoft.jf.config.htmlarea.service.IHtmlAreaService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.cache.JFCache;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName HtmlAreaService
 * @Description 富文本Servcie实现类
 * @author bchen
 * @created 2013-11-19 11:35:45
 */
@Named
@JResource(code = HtmlAreaConstant.RESOURCE_CODE, descCN = HtmlAreaConstant.RESOURCE_DESC_CN, descEN = HtmlAreaConstant.RESOURCE_DESC_EN)
public class HtmlAreaService implements IHtmlAreaService,IRestService {

	@Inject
	private IHtmlAreaDao htmlAreaDao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = HtmlAreaConstant.LIST_DESC_CN, descEN = HtmlAreaConstant.LIST_DESC_EN)
	public PagedResult<HtmlAreaVO> findHtmlAreaList(HtmlAreaVO htmlArea,
			PageVO page) throws ApplicationException {
		return htmlAreaDao.findHtmlAreaList(htmlArea, page);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = HtmlAreaConstant.CREATE_DESC_CN, descEN = HtmlAreaConstant.CREATE_DESC_EN,isAudit=true)
	public void createHtmlArea(HtmlAreaVO htmlArea) throws ApplicationException {
		htmlAreaDao.createHtmlArea(htmlArea);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = HtmlAreaConstant.SINGLE_DESC_CN, descEN = HtmlAreaConstant.SINGLE_DESC_EN)
	public HtmlAreaVO findHtmlArea(HtmlAreaVO htmlArea)
			throws ApplicationException {
		return htmlAreaDao.findHtmlArea(htmlArea);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = HtmlAreaConstant.UPDATE_DESC_CN, descEN = HtmlAreaConstant.UPDATE_DESC_EN,isAudit=true)
	public void updateHtmlArea(HtmlAreaVO htmlArea) throws ApplicationException {
		htmlAreaDao.updateHtmlArea(htmlArea);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = HtmlAreaConstant.DELETE_DESC_CN, descEN = HtmlAreaConstant.DELETE_DESC_EN,isAudit=true)
	public void deleteHtmlArea(HtmlAreaVO htmlArea) throws ApplicationException {
		htmlAreaDao.deleteHtmlArea(htmlArea);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CLEAR_CACHE, descCN = HtmlAreaConstant.CLEAR_CACHE_DESC_CN, descEN = HtmlAreaConstant.CLEAR_CACHE_DESC_EN,isAudit=true)
	public void clearCache() throws ApplicationException {
		JFCache.clearCache("htmlarea");
	}

}
