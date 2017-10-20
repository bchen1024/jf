package org.btsoft.jf.monitor.api.service;

import java.util.List;

import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.monitor.api.entity.ServiceListVO;

/**
 * @ClassName IServiceScan
 * @Description 服务扫描接口
 * @author bchen
 * @created 2017年7月19日 下午10:59:33
 */
public interface IApiScanService {

	/**
	 * @Description 扫描返回的服务列表
	 * @return
	 * @author bchen
	 * @created 2017年7月19日 下午11:00:20
	 */
	public List<ServiceListVO> getServiceData() throws ApplicationException;

	/**
	 * @Description 存储服务数据
	 * @param serviceList
	 * @author bchen
	 * @created 2017年7月19日 下午11:02:47
	 */
	public void storageServiceData(List<ServiceListVO> serviceList) throws ApplicationException;
}
