package org.btsoft.jf.security.group.entity;

import org.btsoft.jf.core.entity.BaseVO;

/**
 * @ClassName GroupVO
 * @Description 用户群组VO
 * @author bchen
 * @created 2013-11-19 11:17:08
 */
public class GroupVO extends BaseVO {

	private static final long serialVersionUID = 5033557578139395200L;

	private Long groupId;// 群组id
	private String groupCode;// 群组编码
	private String groupName;// 群组名称
	private String applyStatus;// 是否可申请
	private Long groupOwner;// 群组责任人
	private String groupOwnerName;// 群组责任人名称
	private String groupDesc;// 群组描述

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getApplyStatus() {
		return applyStatus;
	}

	public void setApplyStatus(String applyStatus) {
		this.applyStatus = applyStatus;
	}

	public Long getGroupOwner() {
		return groupOwner;
	}

	public void setGroupOwner(Long groupOwner) {
		this.groupOwner = groupOwner;
	}

	public String getGroupOwnerName() {
		return groupOwnerName;
	}

	public void setGroupOwnerName(String groupOwnerName) {
		this.groupOwnerName = groupOwnerName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}
}
