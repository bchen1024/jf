package org.btsoft.jf.config.htmlarea.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.config.htmlarea.entity.HtmlAreaVO;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName IHtmlAreaService
 * @Description 富文本Service接口
 * @author bchen
 * @created 2013-11-19 11:35:06
 */
@Path("/htmlarea")
@Produces(MediaType.APPLICATION_JSON)
public interface IHtmlAreaService {
	/**
	 * @Description 分页获取富文本信息
	 * @param htmlArea
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2014-6-26 下午10:49:33
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<HtmlAreaVO> findHtmlAreaList(@QueryParam("") HtmlAreaVO htmlArea, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 新增富文本信息
	 * @param htmlArea
	 * @return
	 * @author bchen
	 * @created 2014-6-27 下午10:17:51
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createHtmlArea(HtmlAreaVO htmlArea) throws ApplicationException;

	/**
	 * @Description 获取单个富文本信息
	 * @param htmlArea
	 * @return
	 * @author bchen
	 * @created 2014-6-27 下午10:23:36
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public HtmlAreaVO findHtmlArea(@QueryParam("") HtmlAreaVO htmlArea) throws ApplicationException;

	/**
	 * @Description 更新单个富文本信息
	 * @param htmlArea
	 * @return
	 * @author bchen
	 * @created 2014-6-28 下午7:17:18
	 */
	@PUT
	@Path(CoreConstant.PATH_UPDATE)
	public void updateHtmlArea(HtmlAreaVO htmlArea) throws ApplicationException;

	/**
	 * @Description 删除富文本信息
	 * @param htmlArea
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-3-24 下午9:09:31
	 */
	@DELETE
	@Path(CoreConstant.PATH_DELETE)
	public void deleteHtmlArea(HtmlAreaVO htmlArea) throws ApplicationException;

	/**
	 * @Description 清除富文本缓存
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-10-1 下午12:44:28
	 */
	@POST
	@Path(CoreConstant.PATH_CLEAR_CACHE)
	public void clearCache() throws ApplicationException;
}
