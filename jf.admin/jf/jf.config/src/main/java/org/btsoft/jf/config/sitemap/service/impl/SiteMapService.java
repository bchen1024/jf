package org.btsoft.jf.config.sitemap.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.sitemap.dao.ISiteMapDao;
import org.btsoft.jf.config.sitemap.entity.SiteMapConstant;
import org.btsoft.jf.config.sitemap.entity.SiteMapVO;
import org.btsoft.jf.config.sitemap.service.ISiteMapService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.utils.TreeUtils;

/**
 * @ClassName SiteMapService
 * @Description 菜单栏目Service实现类
 * @author bchen
 * @created 2017年8月1日 下午11:43:43
 */
@Named
@JResource(code=SiteMapConstant.RESOURCE_CODE,descCN=SiteMapConstant.RESOURCE_DESC_CN,descEN=SiteMapConstant.RESOURCE_DESC_EN)
public class SiteMapService implements ISiteMapService, IRestService {

	@Inject
	private ISiteMapDao dao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = SiteMapConstant.LIST_DESC_CN, descEN = SiteMapConstant.LIST_DESC_EN)
	public List<Map<String, Object>> findSiteMapList(SiteMapVO siteMap) throws ApplicationException {
		List<Map<String, Object>> result = dao.findSiteMapList(siteMap);
		return TreeUtils.listToTreeList(result, 0L, "siteMapId", "parentId");
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = SiteMapConstant.SINGLE_DESC_CN, descEN = SiteMapConstant.SINGLE_DESC_EN)
	public SiteMapVO findSiteMap(SiteMapVO siteMap) throws ApplicationException {
		return dao.findSiteMap(siteMap);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = SiteMapConstant.CREATE_DESC_CN, descEN = SiteMapConstant.CREATE_DESC_EN, isAudit = true)
	public void createSiteMap(SiteMapVO siteMap) throws ApplicationException {
		dao.createSiteMap(siteMap);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = SiteMapConstant.UPDATE_DESC_CN, descEN = SiteMapConstant.UPDATE_DESC_EN, isAudit = true)
	public void updateSiteMap(SiteMapVO siteMap) throws ApplicationException {
		dao.updateSiteMap(siteMap);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = SiteMapConstant.DELETE_DESC_CN, descEN = SiteMapConstant.DELETE_DESC_EN, isAudit = true)
	public void deleteSiteMap(SiteMapVO siteMap) throws ApplicationException {
		dao.deleteSiteMap(siteMap);
	}

	@Override
	@JOperator(code = "parents", descCN = SiteMapConstant.PARENT_lIST_DESC_CN, descEN = SiteMapConstant.PARENT_LIST_DESC_EN)
	public List<SiteMapVO> findParentSiteMap(SiteMapVO siteMap) throws ApplicationException {
		return dao.findParentSiteMap(siteMap);
	}

	@Override
	public List<SiteMapVO> findSiteMapRouters(SiteMapVO siteMap) throws ApplicationException {
		return dao.findSiteMapRouters(siteMap);
	}

}
