package org.btsoft.jf.config.personalized.service.impl;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.personalized.dao.IPersonalizedDao;
import org.btsoft.jf.config.personalized.entity.PersonalizedConstant;
import org.btsoft.jf.config.personalized.entity.PersonalizedVO;
import org.btsoft.jf.config.personalized.service.IGlobalPersonalizedService;
import org.btsoft.jf.config.personalized.service.IPersonalizedService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName PersonalizedService
 * @Description 个性化设置Service实现类
 * @author bchen
 * @created 2017年8月5日 下午12:56:19
 */
@Named
public class PersonalizedService implements IPersonalizedService, IRestService {

	@Inject
	private IPersonalizedDao dao;
	
	@Inject
	private IGlobalPersonalizedService globalService;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = PersonalizedConstant.LIST_DESC_CN, descEN = PersonalizedConstant.LIST_DESC_EN)
	public PagedResult<PersonalizedVO> findPersonalizedList(PersonalizedVO personalized, PageVO page)
			throws ApplicationException {
		return dao.findPersonalizedList(personalized, page);
	}

	@Override
	public void saveGlobalPersonalized(HashMap<String, Object> content) throws ApplicationException {
		globalService.saveGlobalPersonalized(content);
	}

}
