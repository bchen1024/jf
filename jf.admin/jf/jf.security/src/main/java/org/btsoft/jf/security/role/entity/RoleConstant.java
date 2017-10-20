package org.btsoft.jf.security.role.entity;

/**
 * @ClassName RoleConstant
 * @Description 角色常量类
 * @author bchen
 * @created 2016-3-16 下午9:33:51
 */
public interface RoleConstant {

	public final static String RESOURCE_CODE = "security.role";

	public final static String RESOURCE_DESC_CN = "角色管理";

	public final static String RESOURCE_DESC_EN = "Role";

	public final static String MODULE = "Role";

	public final static String LIST_DESC_CN = "角色列表";

	public final static String LIST_DESC_EN = "Role List";

	public final static String SINGLE_DESC_CN = "查看角色";

	public final static String SINGLE_DESC_EN = "Role Info";

	public final static String CREATE_DESC_CN = "创建角色";

	public final static String CREATE_DESC_EN = "Create Role";

	public final static String UPDATE_DESC_CN = "更新角色";

	public final static String UPDATE_DESC_EN = "Update Role";

	public final static String DELETE_DESC_CN = "删除角色";

	public final static String DELETE_DESC_EN = "Delete Role";
	
	public final static String ROLE_USER_LIST_PATH = "/roleUsers/page";

	public final static String ROLE_USER_DELETE_PATH = "/roleUsers/delete";

	public final static String ROLE_USER_BIND_PATH = "/roleUsers/bind";

	public final static String ROLE_USER_NO_BIND_PATH = "/roleUsers/noBind";

	public final static String ROLE_USER_LIST = "roleUsers";

	public final static String ROLE_USER_LIST_DESC_CN = "角色用户列表";

	public final static String ROLE_USER_LIST_DESC_EN = "Role User List";

	public final static String ROLE_USER_DELETE_MSG= "delete role user relations successful";
	
	public final static String ROLE_USER_CREATE_MSG= "create role user relations successful";
	
	public final static String ROLE_GROUP_LIST_PATH = "/roleGroups/page";

	public final static String ROLE_GROUP_DELETE_PATH = "/roleGroups/delete";

	public final static String ROLE_GROUP_BIND_PATH = "/roleGroups/bind";

	public final static String ROLE_GROUP_NO_BIND_PATH = "/roleGroups/noBind";

	public final static String ROLE_GROUP_LIST = "roleGroups";

	public final static String ROLE_GROUP_LIST_DESC_CN = "角色群组列表";

	public final static String ROLE_GROUP_LIST_DESC_EN = "Role User List";

	public final static String ROLE_GROUP_DELETE_MSG= "delete role group relations successful";
	
	public final static String ROLE_GROUP_CREATE_MSG= "create role group relations successful";
	
	public final static String ROLE_MENU_TREE_PATH = "/roleMenus/tree";
	
	public final static String ROLE_MENU_CREATE_PATH = "/roleMenus/create";
	
	public final static String ROLE_MENU_TREE = "roleMenus";

	public final static String ROLE_MENU_TREE_DESC_CN = "角色菜单树";

	public final static String ROLE_MENU_TREE_DESC_EN = "Role Menu Tree";
	
	public final static String ROLE_MENU_CREATE_MSG= "create role menu relations successful";
	
	public final static String ROLE_PERMISSION_TREE_PATH = "/rolePermissions/tree";
	
	public final static String ROLE_PERMISSION_CREATE_PATH = "/rolePermissions/create";
	
	public final static String ROLE_PERMISSION_TREE = "rolePermissions";

	public final static String ROLE_PERMISSION_TREE_DESC_CN = "角色权限树";

	public final static String ROLE_PERMISSION_TREE_DESC_EN = "Role Permission Tree";
	
	public final static String ROLE_PERMISSION_CREATE_MSG= "create role permission relations successful";

}
