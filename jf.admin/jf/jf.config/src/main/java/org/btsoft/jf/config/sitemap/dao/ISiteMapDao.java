package org.btsoft.jf.config.sitemap.dao;

import java.util.List;
import java.util.Map;

import org.btsoft.jf.config.sitemap.entity.SiteMapVO;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName ISiteMapDao
 * @Description 菜单栏目dao接口
 * @author bchen
 * @created 2017年8月1日 下午11:44:31
 */
public interface ISiteMapDao {

	/**
	 * @Description 获取菜单栏目列表
	 * @param siteMap
	 * @return
	 * @author bchen
	 * @created 2017年8月1日 下午11:35:41
	 */
	public List<Map<String, Object>> findSiteMapList(SiteMapVO siteMap);
	
	/**
	 * @Description 获取单个菜单栏目
	 * @param siteMap
	 * @return
	 * @author bchen
	 * @created 2017年8月6日 下午6:21:58
	 */
	public SiteMapVO findSiteMap(SiteMapVO siteMap);

	/**
	 * @Description 创建菜单栏目
	 * @param siteMap
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月1日 下午11:39:52
	 */
	public void createSiteMap(SiteMapVO siteMap);

	/**
	 * @Description 更新菜单栏目
	 * @param siteMap
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月1日 下午11:39:52
	 */
	public void updateSiteMap(SiteMapVO siteMap);

	/**
	 * @Description 删除菜单栏目
	 * @param siteMap
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月1日 下午11:39:52
	 */
	public void deleteSiteMap(SiteMapVO siteMap);

	/**
	 * @Description 获取父级菜单
	 * @param siteMap
	 * @return
	 * @author bchen
	 * @created 2017年8月1日 下午11:42:49
	 */
	public List<SiteMapVO> findParentSiteMap(SiteMapVO siteMap);
	
	/**
	 * @Description 获取当前配置的菜单
	 * @param siteMap
	 * @return
	 * @author bchen
	 * @created 2017年9月20日 下午10:44:45
	 */
	public List<SiteMapVO> findSiteMapRouters(SiteMapVO siteMap);

}
