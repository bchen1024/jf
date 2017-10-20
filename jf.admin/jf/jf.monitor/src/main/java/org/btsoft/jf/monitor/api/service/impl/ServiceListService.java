package org.btsoft.jf.monitor.api.service.impl;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.monitor.api.dao.IServiceListDao;
import org.btsoft.jf.monitor.api.entity.ServiceListVO;
import org.btsoft.jf.monitor.api.service.IApiScanService;
import org.btsoft.jf.monitor.api.service.IServiceListService;

/**
 * @ClassName ServiceListService
 * @Description 服务接口实现类
 * @author bchen
 * @created 2017年8月3日 上午12:35:54
 */
@Named
public class ServiceListService implements IServiceListService, IRestService {
	
	@Inject
	private IServiceListDao dao;
	
	@Override
	public PagedResult<ServiceListVO> findServiceList(ServiceListVO serviceList, PageVO page)
			throws ApplicationException {
		return dao.findServiceList(serviceList, page);
	}

	@Override
	public void syncService(Map<String, Object> params) throws ApplicationException {
		String serviceType=params.get("serviceType").toString();
		
		//删除该类型的数据
		ServiceListVO serviceList=new ServiceListVO();
		serviceList.setServiceType(serviceType);
		dao.deleteServiceList(serviceList);
		
		//扫描该类型的数据
		IApiScanService service=JF.getContext().getBean("ApiScan."+serviceType, IApiScanService.class);
		service.storageServiceData(service.getServiceData());
	}

}
