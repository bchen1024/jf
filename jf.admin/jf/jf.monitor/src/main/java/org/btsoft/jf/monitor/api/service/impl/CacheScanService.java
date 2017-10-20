package org.btsoft.jf.monitor.api.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.core.cache.ICache;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.utils.CollectionUtils;
import org.btsoft.jf.monitor.api.dao.IServiceListDao;
import org.btsoft.jf.monitor.api.entity.ServiceListVO;
import org.btsoft.jf.monitor.api.service.IApiScanService;
import org.btsoft.jf.monitor.utils.ServiceListUtils;
import org.springframework.aop.support.AopUtils;

/**
 * @ClassName CacheScanService
 * @Description 缓存扫描类
 * @author bchen
 * @created 2017年7月21日 下午6:56:39
 */
@Named("ApiScan.cache")
public class CacheScanService implements IApiScanService {
	
	@Inject
	private IServiceListDao dao;

	@Override
	public List<ServiceListVO> getServiceData() throws ApplicationException {
		List<ServiceListVO> serviceList = new ArrayList<ServiceListVO>();
		Map<String,ICache> cacheBeans=JF.getContext().getBeansOfType(ICache.class);
		for (Iterator<String> iterator = cacheBeans.keySet().iterator(); iterator.hasNext();) {
			ServiceListVO service = new ServiceListVO();
			String beanName=iterator.next();
			Class<?> clazz = AopUtils.getTargetClass(cacheBeans.get(beanName));
			service.setBeanName(beanName);
			service.setClassName(clazz.getName());
			service.setServiceType(ServiceListUtils.SERVICE_TYPE_CACHE);
			serviceList.add(service);
			
		}
		return serviceList;
	}

	@Override
	public void storageServiceData(List<ServiceListVO> serviceList) throws ApplicationException {
		if(!CollectionUtils.isNullOrEmpty(serviceList)){
			dao.insertServiceList(serviceList);
		}
	}

}
