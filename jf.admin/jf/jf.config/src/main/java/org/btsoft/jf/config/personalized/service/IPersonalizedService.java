package org.btsoft.jf.config.personalized.service;

import java.util.HashMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.btsoft.jf.config.personalized.entity.PersonalizedVO;
import org.btsoft.jf.core.constants.CoreConstant;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName IPersonalizedDao
 * @Description 个性化设置Service接口
 * @author bchen
 * @created 2017年8月5日 下午12:52:30
 */
@Path("/personalized")
@Produces("application/json")
public interface IPersonalizedService {

	/**
	 * @Description 分页获取个性化设置列表
	 * @param personalized
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2017年8月5日 下午12:53:27
	 */
	@GET
	@Path(CoreConstant.PATH_LIST)
	public PagedResult<PersonalizedVO> findPersonalizedList(@QueryParam("") PersonalizedVO personalized,
			@QueryParam("") PageVO page) throws ApplicationException;

	/**
	 * @Description 保存全局设置
	 * @param content
	 * @author bchen
	 * @created 2017年9月24日 下午11:28:39
	 */
	@POST
	@Path("/save/global")
	public void saveGlobalPersonalized(HashMap<String, Object> content) throws ApplicationException;
}
