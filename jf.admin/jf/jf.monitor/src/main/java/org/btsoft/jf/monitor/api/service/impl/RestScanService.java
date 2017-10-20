package org.btsoft.jf.monitor.api.service.impl;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Path;

import org.apache.log4j.Logger;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.utils.CollectionUtils;
import org.btsoft.jf.core.utils.StringUtils;
import org.btsoft.jf.monitor.api.dao.IServiceListDao;
import org.btsoft.jf.monitor.api.entity.ServiceListVO;
import org.btsoft.jf.monitor.api.service.IApiScanService;
import org.btsoft.jf.monitor.utils.ServiceListUtils;
import org.springframework.aop.support.AopUtils;

/**
 * @ClassName RestServiceScan
 * @Description Rest服务扫描器
 * @author bchen
 * @created 2017年7月19日 下午11:03:54
 */
@Named("ApiScan.rest")
public class RestScanService implements IApiScanService {

	private final static Logger logger = Logger.getLogger(RestScanService.class);
	
	@Inject
	private IServiceListDao dao;

	@Override
	public List<ServiceListVO> getServiceData() throws ApplicationException {
		logger.info("scaning rest service start...");
		List<ServiceListVO> serviceList = new ArrayList<ServiceListVO>();
		Map<String, Object> resetBeanMap =JF.getContext().getBeansWithAnnotation(Path.class);

		ServiceListUtils.scanBeanAddress();

		for (Iterator<String> iterator = resetBeanMap.keySet().iterator(); iterator.hasNext();) {
			String beanName = iterator.next();
			Class<?> clazz = AopUtils.getTargetClass(resetBeanMap.get(beanName));
			Method[] methods = clazz.getDeclaredMethods();
			if (!CollectionUtils.isNullOrEmpty(methods)) {
				for (int j = 0; j < methods.length; j++) {
					Method method = methods[j];
					// 如果不是public方法，继续下一个方法
					if (!Modifier.isPublic(method.getModifiers())) {
						continue;
					}
					Map<String,String> result=ServiceListUtils.getRestInfo(clazz, method, beanName);
					if(StringUtils.isEmpty(result.get(ServiceListUtils.SERVICE_REST_URL))) {
						continue;
					}
					
					ServiceListVO service = new ServiceListVO();
					service.setBeanName(beanName);
					service.setClassName(clazz.getName());
					service.setMethodName(method.getName());
					service.setMethodReturn(method.getReturnType().getCanonicalName());
					service.setMethodArgs(ServiceListUtils.getMethodArgs(method));
					service.setMethodException(ServiceListUtils.getMethodException(method));
					service.setMethodPermission(ServiceListUtils.getMethodPermission(clazz, method));
					service.setMethodAudit(ServiceListUtils.getMethodAudit(method));
					service.setMethodDesc(ServiceListUtils.getMethodDesc(method));
					service.setServiceType(ServiceListUtils.SERVICE_TYPE_REST);
					
					service.setRestMethod(result.get(ServiceListUtils.SERVICE_REST_METHOD));
					service.setRestUrl(result.get(ServiceListUtils.SERVICE_REST_URL));
					service.setRestProduces(result.get(ServiceListUtils.SERVICE_REST_PRODUCES));
					serviceList.add(service);
				}
			}

		}
		logger.info("scaning rest service end...,rest count:"+serviceList.size());
		return serviceList;
	}

	@Override
	public void storageServiceData(List<ServiceListVO> serviceList) throws ApplicationException {
		if(!CollectionUtils.isNullOrEmpty(serviceList)){
			dao.insertServiceList(serviceList);
		}
	}
}
