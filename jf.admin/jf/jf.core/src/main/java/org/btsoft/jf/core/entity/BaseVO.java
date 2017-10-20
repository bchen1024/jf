package org.btsoft.jf.core.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.btsoft.jf.core.application.IUserPrincipal;
import org.btsoft.jf.core.content.RequestContext;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

/**
 * @ClassName BaseVO
 * @Description 基础的VO类，定义一些公用的属性，例如创建人，创建时间，更新人，更新时间等字段
 * @author bchen
 * @created 2017年6月29日 下午10:38:57
 */
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(value = { "currentUserId", "currentLanguage", "createBy", "lastUpdateBy","appName" })
@JsonSerialize(include = Inclusion.NON_NULL)
public class BaseVO implements Serializable {

	private static final long serialVersionUID = 109144681978814146L;

	/**
	 * 有效标志,可选值Y/N
	 */
	private String enableFlag;

	/**
	 * 创建者账号id
	 */
	private Long createBy;

	/**
	 * 创建者账号名称
	 */
	private String createUserAccount;

	/**
	 * 创建时间
	 */
	private Date creationDate;

	/**
	 * 最后更新者账号ID
	 */
	private Long lastUpdateBy;

	/**
	 * 最后更新者账号名称
	 */
	private String updateUserAccount;

	/**
	 * 最后更新时间
	 */
	private Date lastUpdationDate;

	/**
	 * 当前登录者id
	 */
	private Long currentUserId;

	/**
	 * 当前语言
	 */
	private String currentLanguage;
	
	/**
	 * 当前应用code
	 */
	private String appName;
	
	/**
	 * 查询参数
	 */
	private String queryParams;

	public String getEnableFlag() {
		return enableFlag;
	}

	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public String getCreateUserAccount() {
		return createUserAccount;
	}

	public void setCreateUserAccount(String createUserAccount) {
		this.createUserAccount = createUserAccount;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getLastUpdateBy() {
		return lastUpdateBy;
	}

	public void setLastUpdateBy(Long lastUpdateBy) {
		this.lastUpdateBy = lastUpdateBy;
	}

	public String getUpdateUserAccount() {
		return updateUserAccount;
	}

	public void setUpdateUserAccount(String updateUserAccount) {
		this.updateUserAccount = updateUserAccount;
	}

	public Date getLastUpdationDate() {
		return lastUpdationDate;
	}

	public void setLastUpdationDate(Date lastUpdationDate) {
		this.lastUpdationDate = lastUpdationDate;
	}

	public Long getCurrentUserId() {
		IUserPrincipal user = RequestContext.getCurrent(true).getUser();
		if (user != null) {
			return user.getUserId();
		} else {
			this.currentUserId = -1L;
		}
		return currentUserId;
	}

	public void setCurrentUserId(Long currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getCurrentLanguage() {
		this.currentLanguage = RequestContext.getCurrent(true).getCurrentLanguage();
		return this.currentLanguage;
	}

	public void setCurrentLanguage(String currentLanguage) {
		this.currentLanguage = currentLanguage;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

}
