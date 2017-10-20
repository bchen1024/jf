package org.btsoft.jf.config.lookup.service;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.btsoft.jf.config.lookup.entity.LookupItemVO;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName ILookupItemService
 * @Description LookupItem service接口
 * @author bchen
 * @created 2017年8月11日 下午9:39:27
 */
@Path("/lookupItem")
@Produces("application/json")
public interface ILookupItemService {

	/**
	 * @Description 分页获取LookupItem配置
	 * @param lookupItem
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:32:52
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<LookupItemVO> findLookupItemList(@QueryParam("") LookupItemVO lookupItem,
			@QueryParam("") PageVO page) throws ApplicationException;

	/**
	 * @Description 获取单个LookupItem
	 * @param lookupItem
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:00
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public LookupItemVO findLookupItem(@QueryParam("") LookupItemVO lookupItem) throws ApplicationException;

	/**
	 * @Description 创建LookupItem
	 * @param lookupItem
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:11
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createLookupItem(LookupItemVO lookupItem) throws ApplicationException;

	/**
	 * @Description 更新LookupItem
	 * @param lookupItem
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:26
	 */
	@PUT
	@Path(CoreConstant.PATH_UPDATE)
	public void updateLookupItem(LookupItemVO lookupItem) throws ApplicationException;

	/**
	 * @Description 删除LookupItem
	 * @param lookupItem
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:38
	 */
	@DELETE
	@Path(CoreConstant.PATH_DELETE)
	public void deleteLookupItem(LookupItemVO lookupItem) throws ApplicationException;

	/**
	 * @Description 根据代码编码获取项目列表
	 * @param lookupItem
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年9月6日 下午10:59:39
	 */
	@GET
	@Path("/find/lookupItems/{codeNo}")
	public List<LookupItemVO> findLookupItemByCodeNo(@PathParam("") LookupItemVO lookupItem)
			throws ApplicationException;
}
