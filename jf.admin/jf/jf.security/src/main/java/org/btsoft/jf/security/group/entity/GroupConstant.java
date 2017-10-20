package org.btsoft.jf.security.group.entity;

/**
 * @ClassName GroupConstant
 * @Description 群组常量类
 * @author bchen
 * @created 2016-3-16 下午9:33:51
 */
public interface GroupConstant {

	public final static String RESOURCE_CODE = "security.group";

	public final static String RESOURCE_DESC_CN = "群组管理";

	public final static String RESOURCE_DESC_EN = "Group";

	public final static String MODULE = "Group";

	public final static String LIST_DESC_CN = "群组列表";

	public final static String LIST_DESC_EN = "Group List";

	public final static String SINGLE_DESC_CN = "查看群组";

	public final static String SINGLE_DESC_EN = "View Group";

	public final static String CREATE_DESC_CN = "创建群组";

	public final static String CREATE_DESC_EN = "Create Group";

	public final static String UPDATE_DESC_CN = "更新群组";

	public final static String UPDATE_DESC_EN = "Update Group";

	public final static String DELETE_DESC_CN = "删除群组";

	public final static String DELETE_DESC_EN = "Delete Group";

	public final static String GROUP_USER_LIST_PATH = "/groupUsers/page";

	public final static String GROUP_USER_DELETE_PATH = "/groupUsers/delete";

	public final static String GROUP_USER_BIND_PATH = "/groupUsers/bind";

	public final static String GROUP_USER_NO_BIND_PATH = "/groupUsers/noBind";

	public final static String GROUP_USER_LIST = "groupUsers";

	public final static String GROUP_USER_LIST_DESC_CN = "群组用户列表";

	public final static String GROUP_USER_LIST_DESC_EN = "Group User List";

	public final static String GROUP_USER_DELETE_MSG= "delete group user relations successful";
	
	public final static String GROUP_USER_CREATE_MSG= "create group user relations successful";
	
	public final static String GROUP_ROLE_LIST_PATH = "/groupRoles/page";

	public final static String GROUP_ROLE_DELETE_PATH = "/groupRoles/delete";

	public final static String GROUP_ROLE_BIND_PATH = "/groupRoles/bind";

	public final static String GROUP_ROLE_NO_BIND_PATH = "/groupRoles/noBind";

	public final static String GROUP_ROLE_LIST = "groupRoles";

	public final static String GROUP_ROLE_LIST_DESC_CN = "群组角色列表";

	public final static String GROUP_ROLE_LIST_DESC_EN = "Group Role List";

	public final static String GROUP_ROLE_DELETE_MSG= "delete group role relations successful";
	
	public final static String GROUP_ROLE_CREATE_MSG= "create group role relations successful";

}
