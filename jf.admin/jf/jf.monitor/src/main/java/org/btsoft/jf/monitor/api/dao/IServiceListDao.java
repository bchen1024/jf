package org.btsoft.jf.monitor.api.dao;

import java.util.List;

import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.monitor.api.entity.ServiceListVO;

/**
 * @ClassName IApiScanDao
 * @Description 服务接口扫描Dao接口
 * @author bchen
 * @created 2017年7月21日 下午11:37:01
 */
public interface IServiceListDao {

	/**
	 * @Description 分页查询服务列表
	 * @param serviceList
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2017年8月3日 上午12:24:33
	 */
	public PagedResult<ServiceListVO> findServiceList(ServiceListVO serviceList, PageVO page);

	/**
	 * @Description 批量插入更新服务接口列表
	 * @param serviceList
	 * @author bchen
	 * @created 2017年7月21日 下午11:38:01
	 */
	public void insertServiceList(List<ServiceListVO> serviceList);
	
	/**
	 * @Description 删除服务清单
	 * @param serviceList
	 * @author bchen
	 * @created 2017年8月5日 上午12:11:39
	 */
	public void deleteServiceList(ServiceListVO serviceList);
}
