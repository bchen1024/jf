package org.btsoft.jf.monitor.log.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.monitor.log.dao.IAuditLogDao;
import org.btsoft.jf.monitor.log.entity.AuditLogVO;
import org.btsoft.jf.monitor.log.service.IAuditLogService;

/**
 * @ClassName AuditLogService
 * @Description 审计日志Service服务类
 * @author bchen
 * @created 2017年8月18日 上午12:16:18
 */
@Named
public class AuditLogService implements IAuditLogService {

	@Inject
	private IAuditLogDao dao;

	@Override
	public void createAuditLog(AuditLogVO log) {
		// TODO 异步记录
		dao.createAuditLog(log);
	}

	@Override
	public PagedResult<AuditLogVO> findAuditLogList(AuditLogVO log, PageVO page) throws ApplicationException {
		return dao.findAuditLogList(log, page);
	}

}
