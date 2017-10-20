package org.btsoft.jf.monitor.log.service.impl;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.cache.JFCache;
import org.btsoft.jf.core.content.JF;
import org.btsoft.jf.core.content.RequestContext;
import org.btsoft.jf.core.utils.CollectionUtils;
import org.btsoft.jf.core.utils.JsonUtils;
import org.btsoft.jf.monitor.log.entity.AuditLogVO;
import org.btsoft.jf.monitor.log.service.IAuditLogService;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.util.ClassUtils;

/**
 * @ClassName AuditLogInterceptor
 * @Description 审计日志拦截器
 * @author bchen
 * @created 2017年8月14日 下午7:11:43
 */
public class AuditLogInterceptor implements MethodInterceptor {
	
	private final static Logger logger=Logger.getLogger(AuditLogInterceptor.class);

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		long startTime=System.currentTimeMillis();
		Object result=invocation.proceed();
		long endTime=System.currentTimeMillis();
		try {
			// 获取方法
			Method method = ClassUtils.getMostSpecificMethod(invocation.getMethod(), invocation.getThis().getClass());
			
			//判断该方法是否有JOperator注解
			if(method.isAnnotationPresent(JOperator.class)) {
				JOperator operator=method.getAnnotation(JOperator.class);
				
				if(operator.isAudit()) {//如果开启了审计，必须记录
					this.auditLog(invocation,startTime,endTime,"audit");
				}else{//如果没开启审计，方法耗时超过性能上限也记录日志
					
					//从数据字典获取性能上限配置，默认为3000
					String perfLimit = JFCache.getCahce("property", "perfLimit", "3000");
					long perfLimitTime=Long.parseLong(perfLimit);
					
					//超过性能上限记录日志
					if((endTime-startTime)>=perfLimitTime) {
						this.auditLog(invocation,startTime,endTime,"perf");
					}
				}
			}
		} catch (Exception e) {
			logger.error(e);
		}
		return result;
	}

	private void auditLog(MethodInvocation invocation,long startTime,long endTime,String logType) throws JsonGenerationException, JsonMappingException, IOException {
		Method method = ClassUtils.getMostSpecificMethod(invocation.getMethod(), invocation.getThis().getClass());
		AuditLogVO logVO=new AuditLogVO();
		RequestContext rc=RequestContext.getCurrent(true);
		logVO.setLogTime(new Date(startTime));
		logVO.setLogAccount("Guest");
		logVO.setLogIp(rc.getHostIp());
		logVO.setLogHost(rc.getHostName());
		logVO.setLogClass(method.getDeclaringClass().getName());
		logVO.setLogMethod(method.getName());
		logVO.setLogStartTime(new Date(startTime));
		logVO.setLogEndTime(new Date(endTime));
		logVO.setLogCast(endTime-startTime);
		logVO.setLogClient(rc.getClient());
		Object urlObj=rc.getItems().get("requestUrl");
		logVO.setLogUrl(urlObj==null?"":urlObj.toString());
		logVO.setLogType(logType);
		Object[] params=invocation.getArguments();
		if(!CollectionUtils.isNullOrEmpty(params)) {
			StringBuffer sb=new StringBuffer();
			for (int i = 0; i < params.length; i++) {
				if(i>0 && i<params.length) {
					sb.append(",");
				}
				sb.append(JsonUtils.objectToJsonStr(params[i],true));
			}
			if(sb.length()>=4000) {
				logVO.setLogMessage(sb.toString().substring(0, 3900)+"...,parameters are long, please check the server logs for details");
				logger.warn("Method["+method.getName()+"] parameter character length exceeds 4000,parameter is "+sb.toString());
			}else {
				logVO.setLogMessage(sb.toString());
			}
		}
		
		IAuditLogService logService=JF.getContext().getBean("auditLogService", IAuditLogService.class);
		logService.createAuditLog(logVO);
	}
}
