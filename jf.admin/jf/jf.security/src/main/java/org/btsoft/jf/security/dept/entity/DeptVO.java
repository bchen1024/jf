package org.btsoft.jf.security.dept.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName DeptVO
 * @Description 部门信息VO
 * @author bchen
 * @created 2016-3-28 下午7:03:48
 */
public class DeptVO extends BaseVO {

	private static final long serialVersionUID = -3201301120828407605L;

	private Long deptId;// 部门id

	private String deptNameCN;// 部门中文名称

	private String deptNameEN;// 部门英文名称
	
	private String deptName; //部门名称

	private String deptDesc;// 部门描述

	private Long _parentId;// 上级部门id

	private String parentDeptName;// 上级部门名称

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptNameCN() {
		return deptNameCN;
	}

	public void setDeptNameCN(String deptNameCN) {
		this.deptNameCN = deptNameCN;
	}

	public String getDeptNameEN() {
		return deptNameEN;
	}

	public void setDeptNameEN(String deptNameEN) {
		this.deptNameEN = deptNameEN;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptDesc() {
		return deptDesc;
	}

	public void setDeptDesc(String deptDesc) {
		this.deptDesc = deptDesc;
	}

	public Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}

	public String getParentDeptName() {
		return parentDeptName;
	}

	public void setParentDeptName(String parentDeptName) {
		this.parentDeptName = parentDeptName;
	}
}
