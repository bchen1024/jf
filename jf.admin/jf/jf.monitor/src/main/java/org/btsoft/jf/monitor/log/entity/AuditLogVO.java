package org.btsoft.jf.monitor.log.entity;

import java.util.Date;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName AuditLogVO
 * @Description 审计日志VO
 * @author bchen
 * @created 2017年8月15日 下午11:51:43
 */
public class AuditLogVO extends BaseVO {

	private static final long serialVersionUID = -4359101249179551087L;

	private Long logId;

	private Date logTime;

	private String logAccount;

	private String logIp;

	private String logHost;

	private String logClass;

	private String logMethod;

	private String logMessage;

	private Date logStartTime;

	private Date logEndTime;

	private long logCast;

	private String logUrl;

	private String logType;

	private String logClient;

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public Date getLogTime() {
		return logTime;
	}

	public void setLogTime(Date logTime) {
		this.logTime = logTime;
	}

	public String getLogAccount() {
		return logAccount;
	}

	public void setLogAccount(String logAccount) {
		this.logAccount = logAccount;
	}

	public String getLogIp() {
		return logIp;
	}

	public void setLogIp(String logIp) {
		this.logIp = logIp;
	}

	public String getLogHost() {
		return logHost;
	}

	public void setLogHost(String logHost) {
		this.logHost = logHost;
	}

	public String getLogClass() {
		return logClass;
	}

	public void setLogClass(String logClass) {
		this.logClass = logClass;
	}

	public String getLogMethod() {
		return logMethod;
	}

	public void setLogMethod(String logMethod) {
		this.logMethod = logMethod;
	}

	public String getLogMessage() {
		return logMessage;
	}

	public void setLogMessage(String logMessage) {
		this.logMessage = logMessage;
	}

	public Date getLogStartTime() {
		return logStartTime;
	}

	public void setLogStartTime(Date logStartTime) {
		this.logStartTime = logStartTime;
	}

	public Date getLogEndTime() {
		return logEndTime;
	}

	public void setLogEndTime(Date logEndTime) {
		this.logEndTime = logEndTime;
	}

	public long getLogCast() {
		return logCast;
	}

	public void setLogCast(long logCast) {
		this.logCast = logCast;
	}

	public String getLogUrl() {
		return logUrl;
	}

	public void setLogUrl(String logUrl) {
		this.logUrl = logUrl;
	}

	public String getLogType() {
		return logType;
	}

	public void setLogType(String logType) {
		this.logType = logType;
	}

	public String getLogClient() {
		return logClient;
	}

	public void setLogClient(String logClient) {
		this.logClient = logClient;
	}
}
