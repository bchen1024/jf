package org.btsoft.jf.config.property.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.property.dao.IPropertyDao;
import org.btsoft.jf.config.property.entity.PropertyConstant;
import org.btsoft.jf.config.property.entity.PropertyVO;
import org.btsoft.jf.config.property.service.IPropertyService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.cache.JFCache;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName PropertyService
 * @Description 数据字典Service实现类
 * @author bchen
 * @created 2013-11-19 11:57:58
 */
@Named
@JResource(code = PropertyConstant.RESOURCE_CODE, descCN = PropertyConstant.RESOURCE_DESC_CN, descEN = PropertyConstant.RESOURCE_DESC_EN)
public class PropertyService implements IPropertyService,IRestService{

	@Inject
	private IPropertyDao propertyDao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = PropertyConstant.LIST_DESC_CN, descEN = PropertyConstant.LIST_DESC_EN)
	public PagedResult<PropertyVO> findPropertyList(PropertyVO property,
			PageVO page) throws ApplicationException {
		return propertyDao.findPropertyList(property, page);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = PropertyConstant.SINGLE_DESC_CN, descEN = PropertyConstant.SINGLE_DESC_EN)
	public PropertyVO findProperty(PropertyVO property)
			throws ApplicationException {
		 PropertyVO result= propertyDao.findProperty(property);
		 return result;
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = PropertyConstant.CREATE_DESC_CN, descEN = PropertyConstant.CREATE_DESC_EN,isAudit=true)
	public void createProperty(PropertyVO property) throws ApplicationException {
		propertyDao.createProperty(property);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = PropertyConstant.UPDATE_DESC_CN, descEN = PropertyConstant.UPDATE_DESC_EN,isAudit=true)
	public void updateProperty(PropertyVO property) throws ApplicationException {
		propertyDao.updateProperty(property);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = PropertyConstant.DELETE_DESC_CN, descEN = PropertyConstant.DELETE_DESC_EN,isAudit=true)
	public void deleteProperty(PropertyVO property) throws ApplicationException {
		propertyDao.deleteProperty(property);
	}

	@Override
	@JOperator(code =CoreConstant.JOPERATOR_CODE_CLEAR_CACHE, descCN = PropertyConstant.CLEAR_CACHE_DESC_CN, descEN = PropertyConstant.CLEAR_CACHE_DESC_EN,isAudit=true)
	public void clearCache() throws ApplicationException {
		JFCache.clearCache("property");
	}
}
