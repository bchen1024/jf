package org.btsoft.jf.monitor.api.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.core.file.IUploadSupport;
import org.btsoft.jf.core.file.UploadSetting;
import org.btsoft.jf.core.utils.CollectionUtils;
import org.btsoft.jf.monitor.api.dao.IServiceListDao;
import org.btsoft.jf.monitor.api.entity.ServiceListVO;
import org.btsoft.jf.monitor.api.service.IApiScanService;
import org.btsoft.jf.monitor.utils.ServiceListUtils;
import org.springframework.aop.support.AopUtils;

/**
 * @ClassName UploadScanService
 * @Description 上传扫描类
 * @author bchen
 * @created 2017年7月21日 下午6:56:39
 */
@Named("ApiScan.upload")
public class UploadScanService implements IApiScanService {
	
	@Inject
	private IServiceListDao dao;

	@Override
	public List<ServiceListVO> getServiceData() throws ApplicationException {
		List<ServiceListVO> serviceList = new ArrayList<ServiceListVO>();
		Map<String,IUploadSupport> uploadBeans=JF.getContext().getBeansOfType(IUploadSupport.class);
		Map<String,String> params=new HashMap<String,String>();
		for (Iterator<String> iterator = uploadBeans.keySet().iterator(); iterator.hasNext();) {
			ServiceListVO service = new ServiceListVO();
			String beanName=iterator.next();
			IUploadSupport upload=uploadBeans.get(beanName);
			UploadSetting setting=upload.uploadSetting(params);
			Class<?> clazz = AopUtils.getTargetClass(upload);
			service.setBeanName(beanName);
			service.setClassName(clazz.getName());
			if(setting!=null) {
				service.setMethodName(setting.getUploadFolder());
				service.setMethodReturn(String.valueOf(setting.getTotalFileNumber()));
				service.setMethodArgs(String.valueOf(setting.getSingleFileSize()));
			}
			service.setServiceType(ServiceListUtils.SERVICE_TYPE_UPLOAD);
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
