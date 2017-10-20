package org.btsoft.jf.config.sitemap.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.btsoft.jf.config.sitemap.entity.SiteMapVO;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName ISiteMapService
 * @Description 菜单栏目Service
 * @author bchen
 * @created 2017年8月1日 下午11:33:40
 */
@Path("/siteMap")
@Produces("application/json")
public interface ISiteMapService {

	/**
	 * @Description 获取菜单栏目列表
	 * @param siteMap
	 * @return
	 * @author bchen
	 * @created 2017年8月1日 下午11:35:41
	 */
	@GET
	@Path("/find/list")
	public List<Map<String, Object>> findSiteMapList(@QueryParam("") SiteMapVO siteMap) throws ApplicationException;
	
	/**
	 * @Description 获取单个菜单栏目
	 * @param siteMap
	 * @return
	 * @author bchen
	 * @created 2017年8月6日 下午6:21:58
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public SiteMapVO findSiteMap(@QueryParam("")SiteMapVO siteMap)throws ApplicationException;

	/**
	 * @Description 创建菜单栏目
	 * @param siteMap
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月1日 下午11:39:52
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createSiteMap(SiteMapVO siteMap) throws ApplicationException;

	/**
	 * @Description 更新菜单栏目
	 * @param siteMap
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月1日 下午11:39:52
	 */
	@PUT
	@Path(CoreConstant.PATH_UPDATE)
	public void updateSiteMap(SiteMapVO siteMap) throws ApplicationException;

	/**
	 * @Description 删除菜单栏目
	 * @param siteMap
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月1日 下午11:39:52
	 */
	@DELETE
	@Path(CoreConstant.PATH_DELETE)
	public void deleteSiteMap(SiteMapVO siteMap) throws ApplicationException;

	/**
	 * @Description 获取父级菜单
	 * @param siteMap
	 * @return
	 * @author bchen
	 * @created 2017年8月1日 下午11:42:49
	 */
	@GET
	@Path("/find/parents")
	public List<SiteMapVO> findParentSiteMap(@QueryParam("")SiteMapVO siteMap) throws ApplicationException;
	
	/**
	 * @Description 获取当前配置的菜单
	 * @param siteMap
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年9月20日 下午10:41:40
	 */
	@GET
	@Path("/find/routers")
	public List<SiteMapVO> findSiteMapRouters(@QueryParam("")SiteMapVO siteMap)throws ApplicationException;
}
