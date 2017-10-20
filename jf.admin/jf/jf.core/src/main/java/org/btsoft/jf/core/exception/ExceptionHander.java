package org.btsoft.jf.core.exception;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.log4j.Logger;

/**
 * @ClassName ExceptionHander
 * @Description 异常处理器
 * @author bchen
 * @created 2014-1-13 12:34:11
 */
@SuppressWarnings("rawtypes")
@Provider
public class ExceptionHander implements ExceptionMapper {

	private static Logger logger = Logger.getLogger(ExceptionHander.class);

	@Override
	public Response toResponse(Throwable ex) {
		Throwable thr = this.genThroable(ex);

		FaultVO fault = new FaultVO();
		this.buildFault(fault, thr);

		Response.ResponseBuilder rb = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		rb.entity(fault);
		rb.status(fault.getHttpCode());
		rb.type("application/json;charset=UTF-8");
		Response r = rb.build();
		return r;
	}

	private Throwable genThroable(Throwable ex) {
		Throwable tmp = ex;
		if (tmp instanceof ApplicationException) {
			return tmp;
		}
		return tmp.getCause() == null ? tmp : tmp.getCause();
	}

	private void buildFault(FaultVO fault, Throwable thr) {
		logger.error(thr.getMessage(), thr);
		String message = thr.getMessage();
		if (thr instanceof NotFoundException) {
			thr = new ApplicationException();
		} else if (message == null) {
			thr = new ApplicationException();
		} else if (message.startsWith("ORA-00001")) {// 违反唯一约束条件
			thr = new ApplicationException("jf.exception.database.notSame");
		} else if (message.startsWith("ORA-01400") || message.startsWith("ORA-01407")) {// 不能将空值入库
			thr = new ApplicationException("jf.exception.database.notNull");
		} else if (message.startsWith("ORA-12899")) {// 内容长度超过了数据库的限制
			thr = new ApplicationException("jf.exception.database.outLength");
		} else if (message.startsWith("ORA-")) {// 数据库异常
			thr = new ApplicationException("jf.exception.database.systemException");
		} else if (thr instanceof ApplicationException) {
		} else {
			thr = new ApplicationException();
		}
		fault.setHttpCode(((ApplicationException)thr).getHttpCode());
		fault.setErrorCode(((ApplicationException)thr).getErrorCode());
		fault.setMessage(thr.getMessage());
	}

}
