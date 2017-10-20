package org.btsoft.jf.security.permission.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.security.permission.entity.PermissionVO;

/**
 * @ClassName IPermissionService
 * @Description 权限点Service接口
 * @author bchen
 * @created 2013-11-19 23:42:14
 */
@Path("/permission")
@Produces(MediaType.APPLICATION_JSON)
public interface IPermissionService {

	/**
	 * @Description 获取权限点Grid树
	 * @param permission
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2014-1-12 21:46:31
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<PermissionVO> findPermissionList(
			@QueryParam("") PermissionVO permission)
			throws ApplicationException;

	/**
	 * @Description 创建单个权限点
	 * @param permission
	 * @author bchen
	 * @created 2014-4-13 18:31:56
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createPermission(PermissionVO permission) throws ApplicationException;

	/**
	 * @Description 获取单个权限点信息
	 * @param permission
	 * @return
	 * @author bchen
	 * @created 2014-4-13 19:05:36
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public PermissionVO findPermission(@QueryParam("") PermissionVO permission)
			throws ApplicationException;

	/**
	 * @Description 更新单个权限点信息
	 * @param permission
	 * @return
	 * @author bchen
	 * @created 2014-4-13 7:18:08
	 */
	@POST
	@Path(CoreConstant.PATH_UPDATE)
	public void updatePermission(PermissionVO permission) throws ApplicationException;

	/**
	 * @Description 删除单个权限点信息
	 * @param permission
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2014-5-1 10:17:35
	 */
	@POST
	@Path(CoreConstant.PATH_DELETE)
	public void deletePermission(PermissionVO permission) throws ApplicationException;
	

	/**
	 * @Description 同步代码权限点到数据库中
	 * @param permission
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-4-10 下午8:26:36
	 */
	@POST
	@Path("/sync")
	public Map<String,Object> syncPermission(PermissionVO permission)throws ApplicationException;
	
	/**
	 * @Description 获取权限点资源
	 * @param permission
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-9-28 上午12:02:33
	 */
	@GET
	@Path("/find/resources")
	public List<PermissionVO> findPermissionResource(@QueryParam("")PermissionVO permission)throws ApplicationException;
	
	/**
	 * @Description 删除无效的权限点
	 * @param permission
	 * @author bchen
	 * @created 2016-9-28 上午12:03:35
	 */
	@POST
	@Path("/deleteInValid")
	public void deleteInValidPermission(PermissionVO permission);
	
	
}
