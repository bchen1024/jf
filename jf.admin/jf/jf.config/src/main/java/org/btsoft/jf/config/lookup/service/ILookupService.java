package org.btsoft.jf.config.lookup.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.btsoft.jf.config.lookup.entity.LookupVO;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName ILookupService
 * @Description Lookup service接口
 * @author bchen
 * @created 2017年8月11日 下午9:39:27
 */
@Path("/lookup")
@Produces("application/json")
public interface ILookupService {

	/**
	 * @Description 分页获取Lookup配置
	 * @param lookup
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:32:52
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<LookupVO> findLookupList(@QueryParam("") LookupVO lookup, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 获取单个Lookup
	 * @param lookup
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:00
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public LookupVO findLookup(@QueryParam("") LookupVO lookup) throws ApplicationException;

	/**
	 * @Description 创建Lookup
	 * @param lookup
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:11
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createLookup(LookupVO lookup) throws ApplicationException;

	/**
	 * @Description 更新Lookup
	 * @param lookup
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:26
	 */
	@PUT
	@Path(CoreConstant.PATH_UPDATE)
	public void updateLookup(LookupVO lookup) throws ApplicationException;

	/**
	 * @Description 删除Lookup
	 * @param lookup
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:38
	 */
	@DELETE
	@Path(CoreConstant.PATH_DELETE)
	public void deleteLookup(LookupVO lookup) throws ApplicationException;
}
