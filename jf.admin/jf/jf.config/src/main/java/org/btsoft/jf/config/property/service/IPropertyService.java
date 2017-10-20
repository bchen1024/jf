package org.btsoft.jf.config.property.service;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.config.property.entity.PropertyVO;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName IPropertyService
 * @Description 数据字典Service接口
 * @author bchen
 * @created 2013-11-19 11:57:38
 */
@Path("/property")
@Produces(MediaType.APPLICATION_JSON)
public interface IPropertyService {

	/**
	 * @Description 分页查询数据字典
	 * @param property
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2013-12-2 11:16:53
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<PropertyVO> findPropertyList(
			@QueryParam("") PropertyVO property, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 查找单个数据字典属性
	 * @param property
	 * @return
	 * @author bchen
	 * @created 2013-12-2 11:19:25
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public PropertyVO findProperty(@QueryParam("") PropertyVO property)
			throws ApplicationException;

	/**
	 * @Description 创建单个字典属性
	 * @author bchen
	 * @created 2013-12-3 9:27:06
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createProperty(PropertyVO property) throws ApplicationException;

	/**
	 * @Description 更新单个数据字典
	 * @param property
	 * @author bchen
	 * @created 2013-12-3 9:29:44
	 */
	@PUT
	@Path(CoreConstant.PATH_UPDATE)
	public void updateProperty(PropertyVO property) throws ApplicationException;

	/**
	 * @Description 删除单个数据字典
	 * @param property
	 * @author bchen
	 * @created 2013-12-3 9:31:02
	 */
	@DELETE
	@Path(CoreConstant.PATH_DELETE)
	public void deleteProperty(PropertyVO property) throws ApplicationException;

	/**
	 * @Description 清除数据字典缓存
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-10-1 下午12:44:28
	 */
	@POST
	@Path(CoreConstant.PATH_CLEAR_CACHE)
	public void clearCache() throws ApplicationException;

}
