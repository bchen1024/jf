package org.btsoft.jf.monitor.log.dao;

import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.monitor.log.entity.AuditLogVO;

/**
 * @ClassName IAuditLogDao
 * @Description 审计日志Dao接口
 * @author bchen
 * @created 2017年8月18日 上午12:05:14
 */
public interface IAuditLogDao {

	/**
	 * @Description 创建审计日志
	 * @param log
	 * @return
	 * @author bchen
	 * @created 2017年8月18日 上午12:06:06
	 */
	int createAuditLog(AuditLogVO log);

	/**
	 * @Description 分页获取审计日志信息
	 * @param log
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2017年8月29日 下午9:20:20
	 */
	PagedResult<AuditLogVO> findAuditLogList(AuditLogVO log, PageVO page);
}
