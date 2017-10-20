package org.btsoft.jf.security.role.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName RoleVO
 * @Description 角色信息VO
 * @author bchen
 * @created 2013-11-5 19:49:48
 */
public class RoleVO extends BaseVO {

	private static final long serialVersionUID = 4114444274326269232L;

	private Long roleId;// 角色id
	private String roleCode;// 角色编码
	private String roleName;// 角色名称
	private String roleDesc;// 角色描述
	private String applyStatus;// 申请状态
	private Long roleOwner;// 角色责任人id
	private String roleOwnerName;// 角色责任人名称

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Long getRoleOwner() {
		return roleOwner;
	}

	public void setRoleOwner(Long roleOwner) {
		this.roleOwner = roleOwner;
	}

	public String getRoleOwnerName() {
		return roleOwnerName;
	}

	public void setRoleOwnerName(String roleOwnerName) {
		this.roleOwnerName = roleOwnerName;
	}
}
