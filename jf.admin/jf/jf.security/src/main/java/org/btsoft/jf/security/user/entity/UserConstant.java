package org.btsoft.jf.security.user.entity;

/**
 * @ClassName UserConstant
 * @Description 用户常量类
 * @author bchen
 * @created 2016-3-16 下午9:33:51
 */
public interface UserConstant {

	public final static String RESOURCE_CODE = "security.user";

	public final static String RESOURCE_DESC_CN = "用户管理";

	public final static String RESOURCE_DESC_EN = "User";

	public final static String MODULE = "User";

	public final static String LIST_DESC_CN = "用户列表";

	public final static String LIST_DESC_EN = "User List";

	public final static String SINGLE_DESC_CN = "查看用户";

	public final static String SINGLE_DESC_EN = "User Info";

	public final static String CREATE_DESC_CN = "创建用户";

	public final static String CREATE_DESC_EN = "Create User";

	public final static String UPDATE_DESC_CN = "更新用户";

	public final static String UPDATE_DESC_EN = "Update User";

	public final static String DELETE_DESC_CN = "删除用户";

	public final static String DELETE_DESC_EN = "Delete User";
	
	public final static String USER_ROLE_LIST_PATH = "/userRoles/page";
	
	public final static String MY_ROLE_LIST_PATH = "/myRoles/page";

	public final static String USER_ROLE_DELETE_PATH = "/userRoles/delete";

	public final static String USER_ROLE_BIND_PATH = "/userRoles/bind";

	public final static String USER_ROLE_NO_BIND_PATH = "/userRoles/noBind";

	public final static String USER_ROLE_LIST = "userRoles";

	public final static String USER_ROLE_LIST_DESC_CN = "用户角色列表";

	public final static String USER_ROLE_LIST_DESC_EN = "User Role List";

	public final static String USER_ROLE_DELETE_MSG= "delete user role relations successful";
	
	public final static String USER_ROLE_CREATE_MSG= "create user role relations successful";
	
	public final static String USER_GROUP_LIST_PATH = "/userGroups/page";
	
	public final static String MY_GROUP_LIST_PATH = "/myGroups/page";

	public final static String USER_GROUP_DELETE_PATH = "/userGroups/delete";

	public final static String USER_GROUP_BIND_PATH = "/userGroups/bind";

	public final static String USER_GROUP_NO_BIND_PATH = "/userGroups/noBind";

	public final static String USER_GROUP_LIST = "userGroups";

	public final static String USER_GROUP_LIST_DESC_CN = "用户群组列表";

	public final static String USER_GROUP_LIST_DESC_EN = "User Group List";

	public final static String USER_GROUP_DELETE_MSG= "delete user group relations successful";
	
	public final static String USER_GROUP_CREATE_MSG= "create user group relations successful";

}
