package org.btsoft.jf.security.dept.service;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;
import org.btsoft.jf.security.dept.entity.DeptVO;

/**
 * @ClassName IDeptService
 * @Description 组织机构Service接口
 * @author bchen
 * @created 2016-3-28 下午7:11:52
 */
@Path("/dept")
@Produces(MediaType.APPLICATION_JSON)
public interface IDeptService {

	/**
	 * @Description 获取组织机构列表
	 * @param dept
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-3-28 下午7:16:53
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<DeptVO> findDeptList(@QueryParam("") DeptVO dept)
			throws ApplicationException;

	/**
	 * @Description 创建组织机构
	 * @param dept
	 * @author bchen
	 * @created 2016-3-28 下午7:08:50
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createDept(DeptVO dept) throws ApplicationException;

	/**
	 * @Description 获取单个组织机构信息
	 * @param dept
	 * @return
	 * @author bchen
	 * @created 2016-3-28 下午7:09:49
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public DeptVO findDept(@QueryParam("") DeptVO dept)
			throws ApplicationException;

	/**
	 * @Description 更新组织机构
	 * @param dept
	 * @author bchen
	 * @created 2016-3-28 下午7:09:21
	 */
	@POST
	@Path(CoreConstant.PATH_UPDATE)
	public void updateDept(DeptVO dept) throws ApplicationException;

	/**
	 * @Description 删除组织机构
	 * @param dept
	 * @author bchen
	 * @created 2016-3-28 下午7:10:26
	 */
	@POST
	@Path(CoreConstant.PATH_DELETE)
	public void deleteDept(DeptVO dept) throws ApplicationException;
}
