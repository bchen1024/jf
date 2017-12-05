package org.btsoft.jf.security.permission.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName PermissionVO
 * @Description 权限VO
 * @author bchen
 * @created 2013-11-19 11:41:31
 */
public class PermissionVO extends BaseVO {
	private static final long serialVersionUID = 82035009337446636L;
	private String permissionCode;// 权限code
	private String permissionDescCN;// 权限中文描述
	private String permissionDescEN;// 权限英文描述
	private String permissionDesc;// 显示名称
	private String permissionType;// 权限类型
	private String parentCode;// 父级code
	private String parentPermissionName;
	private String permissionUrl;// 绑定url
	private String permissionSource;// 权限点来源 custom:自定义手动添加；annotation:注解扫描

	public String getPermissionCode() {
		return permissionCode;
	}

	public void setPermissionCode(String permissionCode) {
		this.permissionCode = permissionCode;
	}

	public String getPermissionDescCN() {
		return permissionDescCN;
	}

	public void setPermissionDescCN(String permissionDescCN) {
		this.permissionDescCN = permissionDescCN;
	}

	public String getPermissionDescEN() {
		return permissionDescEN;
	}

	public void setPermissionDescEN(String permissionDescEN) {
		this.permissionDescEN = permissionDescEN;
	}

	public String getPermissionDesc() {
		return permissionDesc;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}

	public String getPermissionType() {
		return permissionType;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	public String getParentPermissionName() {
		return parentPermissionName;
	}

	public void setParentPermissionName(String parentPermissionName) {
		this.parentPermissionName = parentPermissionName;
	}

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	public String getPermissionSource() {
		return permissionSource;
	}

	public void setPermissionSource(String permissionSource) {
		this.permissionSource = permissionSource;
	}
}
