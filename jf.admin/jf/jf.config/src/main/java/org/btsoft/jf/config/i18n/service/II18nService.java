package org.btsoft.jf.config.i18n.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.config.i18n.entity.I18nVO;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName II18nService
 * @Description 国际化Service接口
 * @author bchen
 * @created 2013-11-19 11:38:13
 */
@Path("/i18n")
@Produces(MediaType.APPLICATION_JSON)
public interface II18nService {

	/**
	 * @Description 分页查询国际化信息
	 * @param i18n
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2013-12-24 10:19:26
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<I18nVO> findI18nList(@QueryParam("") I18nVO i18n, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 获取单个国际化信息
	 * @param i18n
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月4日 下午11:39:42
	 */
	@GET
	@Path(CoreConstant.PATH_SINGLE)
	public I18nVO findI18n(@QueryParam("") I18nVO i18n) throws ApplicationException;

	/**
	 * @Description 创建国际化信息
	 * @param i18n
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月4日 下午11:39:42
	 */
	@POST
	@Path(CoreConstant.PATH_CREATE)
	public void createI18n(I18nVO i18n) throws ApplicationException;

	/**
	 * @Description 更新国际化信息
	 * @param i18n
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月4日 下午11:39:42
	 */
	@PUT
	@Path(CoreConstant.PATH_UPDATE)
	public void updateI18n(I18nVO i18n) throws ApplicationException;

	/**
	 * @Description 删除国际化信息
	 * @param i18n
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月4日 下午11:39:42
	 */
	@DELETE
	@Path(CoreConstant.PATH_DELETE)
	public void deleteI18n(I18nVO i18n) throws ApplicationException;

	/**
	 * @Description 清除国际化信息缓存
	 * @param i18n
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年8月4日 下午11:39:42
	 */
	@POST
	@Path(CoreConstant.PATH_CLEAR_CACHE)
	public void clearCache() throws ApplicationException;
	
	/**
	 * @Description 获取所有的国际化配置数据
	 * @param i18n
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年9月24日 上午12:40:11
	 */
	@GET
	@Path("/find/i18ns")
	public List<Map<String,String>> findI18ns(@QueryParam("")I18nVO i18n)throws ApplicationException;

}
