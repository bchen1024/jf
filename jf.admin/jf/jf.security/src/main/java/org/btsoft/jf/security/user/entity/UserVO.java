package org.btsoft.jf.security.user.entity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import org.btsoft.jf.core.application.IUserPrincipal;
import org.btsoft.jf.core.entity.BaseVO;
import org.btsoft.jf.security.relation.entity.RelationVO;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * @ClassName UserVO
 * @Description 用户VO
 * @author bchen
 * @created 2013-11-20 12:00:41
 */
@XmlRootElement
@JsonIgnoreProperties(value = { "userPassword", "currentUserId",
		"currentLanguage" })
public class UserVO extends BaseVO implements IUserPrincipal {

	private static final long serialVersionUID = -170288745059405772L;

	private Long userId;// 用户id
	private String userName;// 用户名称
	private String userAccount;// 用户账号
	private String userPassword;// 用户密码
	private String userEmail;// 用户邮箱
	private String userPhone;// 用户电话
	private String userDesc;// 用户描述
	private int userTimeout;// 用户密码有效期
	private int passwordError;// 密码错误次数
	private Date lockTime; //锁定时间
	private String displayName;//用户显示名称
	private List<RelationVO> roles;// 用户所有角色
	private List<String> permissions;// 用户权限点
	private Map<String,Object> settings;
	private List<Map<String,Object>> menus;
	private Map<String,Object> global;
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserDesc() {
		return userDesc;
	}

	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}

	public int getUserTimeout() {
		return userTimeout;
	}

	public void setUserTimeout(int userTimeout) {
		this.userTimeout = userTimeout;
	}

	
	public int getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(int passwordError) {
		this.passwordError = passwordError;
	}

	public Date getLockTime() {
		return lockTime;
	}

	public void setLockTime(Date lockTime) {
		this.lockTime = lockTime;
	}

	public List<RelationVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RelationVO> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public Map<String, Object> getSettings() {
		return settings;
	}

	public void setSettings(Map<String, Object> settings) {
		this.settings = settings;
	}

	public List<Map<String, Object>> getMenus() {
		return menus;
	}

	public void setMenus(List<Map<String, Object>> menus) {
		this.menus = menus;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public void setGlobal(Map<String, Object> global) {
		this.global = global;
	}

	public Map<String, Object> getGlobal() {
		return global;
	}

}
