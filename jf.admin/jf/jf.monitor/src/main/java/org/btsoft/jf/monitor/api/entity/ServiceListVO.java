package org.btsoft.jf.monitor.api.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName ServiceListVO
 * @Description 服务清单VO
 * @author bchen
 * @created 2017年7月19日 下午9:56:48
 */
public class ServiceListVO extends BaseVO {

	private static final long serialVersionUID = -2543932239347867364L;

	/**
	 * 服务主键id
	 */
	private Long serviceId;

	/**
	 * Bean名称
	 */
	private String beanName;

	/**
	 * class名称
	 */
	private String className;

	/**
	 * 方法名称
	 */
	private String methodName;

	/**
	 * 方法返回值
	 */
	private String methodReturn;

	/**
	 * 方法参数
	 */
	private String methodArgs;

	/**
	 * 方法异常
	 */
	private String methodException;

	/**
	 * 方法权限点
	 */
	private String methodPermission;

	/**
	 * 方法是否开启审计
	 */
	private String methodAudit;

	/**
	 * 方法描述
	 */
	private String methodDesc;

	/**
	 * 服务类型
	 */
	private String serviceType;

	/**
	 * 请求方法类型
	 */
	private String restMethod;

	/**
	 * 请求url
	 */
	private String restUrl;

	/**
	 * 方法返回数据格式
	 */
	private String restProduces;

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethodName() {
		return methodName;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public String getMethodReturn() {
		return methodReturn;
	}

	public void setMethodReturn(String methodReturn) {
		this.methodReturn = methodReturn;
	}

	public String getMethodArgs() {
		return methodArgs;
	}

	public void setMethodArgs(String methodArgs) {
		this.methodArgs = methodArgs;
	}

	public String getMethodException() {
		return methodException;
	}

	public void setMethodException(String methodException) {
		this.methodException = methodException;
	}

	public String getMethodPermission() {
		return methodPermission;
	}

	public void setMethodPermission(String methodPermission) {
		this.methodPermission = methodPermission;
	}

	public String getMethodAudit() {
		return methodAudit;
	}

	public void setMethodAudit(String methodAudit) {
		this.methodAudit = methodAudit;
	}

	public String getMethodDesc() {
		return methodDesc;
	}

	public void setMethodDesc(String methodDesc) {
		this.methodDesc = methodDesc;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}

	public String getRestMethod() {
		return restMethod;
	}

	public void setRestMethod(String restMethod) {
		this.restMethod = restMethod;
	}

	public String getRestUrl() {
		return restUrl;
	}

	public void setRestUrl(String restUrl) {
		this.restUrl = restUrl;
	}

	public String getRestProduces() {
		return restProduces;
	}

	public void setRestProduces(String restProduces) {
		this.restProduces = restProduces;
	}

}
