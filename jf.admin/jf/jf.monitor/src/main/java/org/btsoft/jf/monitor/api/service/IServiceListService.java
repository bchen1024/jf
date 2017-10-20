package org.btsoft.jf.monitor.api.service;

import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.monitor.api.entity.ServiceListVO;

/**
 * @ClassName IApiScanDao
 * @Description 服务接口扫描Dao接口
 * @author bchen
 * @created 2017年7月21日 下午11:37:01
 */
@Path("/service")
@Produces("application/json")
public interface IServiceListService {

	/**
	 * @Description 分页查询服务列表
	 * @param serviceList
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2017年8月3日 上午12:24:33
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<ServiceListVO> findServiceList(@QueryParam("") ServiceListVO serviceList,
			@QueryParam("") PageVO page) throws ApplicationException;

	/**
	 * @Description 同步服务数据
	 * @param params
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月5日 上午12:15:41
	 */
	@POST
	@Path("/sync")
	public void syncService(Map<String, Object> params) throws ApplicationException;

}
