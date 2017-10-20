package org.btsoft.jf.monitor.api.service.impl;

import java.util.List;

import javax.inject.Named;

import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.monitor.api.entity.ServiceListVO;
import org.btsoft.jf.monitor.api.service.IApiScanService;

/**
 * @ClassName ServletServiceScan
 * @Description servlet扫描类
 * @author bchen
 * @created 2017年7月21日 下午6:55:11
 */
@Named("ApiScan.servlet")
public class ServletScanService implements IApiScanService {

	@Override
	public List<ServiceListVO> getServiceData() throws ApplicationException {
		return null;
	}

	@Override
	public void storageServiceData(List<ServiceListVO> serviceList) throws ApplicationException {

	}

}
