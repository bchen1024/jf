package org.btsoft.jf.monitor.log.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.monitor.log.entity.AuditLogVO;

/**
 * @ClassName IAuditLogService
 * @Description 审计日志Service类
 * @author bchen
 * @created 2017年8月18日 上午12:15:16
 */
@Path("/auditLog")
@Produces("application/json")
public interface IAuditLogService {

	/**
	 * @Description 创建审计日志
	 * @param log
	 * @return
	 * @author bchen
	 * @created 2017年8月18日 上午12:06:06
	 */
	public void createAuditLog(AuditLogVO log);
	
	/**
	 * @Description 分页获取审计日志信息
	 * @param log
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2017年8月29日 下午9:20:20
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<AuditLogVO> findAuditLogList(@QueryParam("") AuditLogVO log, @QueryParam("") PageVO page)
			throws ApplicationException;
}
