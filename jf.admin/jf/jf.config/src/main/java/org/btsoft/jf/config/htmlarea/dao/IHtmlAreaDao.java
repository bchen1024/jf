package org.btsoft.jf.config.htmlarea.dao;

import org.btsoft.jf.config.htmlarea.entity.HtmlAreaVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;

/**
 * @ClassName IHtmlAreaDao
 * @Description 富文本Dao接口
 * @author bchen
 * @created 2013-11-19 11:34:47
 */
public interface IHtmlAreaDao {

	/**
	 * @Description 分页获取富文本信息
	 * @param htmlArea
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2014-6-26 下午10:49:33
	 */
	PagedResult<HtmlAreaVO> findHtmlAreaList(HtmlAreaVO htmlArea, PageVO page);

	/**
	 * @Description 新增富文本信息
	 * @param htmlArea
	 * @return
	 * @author bchen
	 * @created 2014-6-27 下午10:17:51
	 */
	int createHtmlArea(HtmlAreaVO htmlArea);

	/**
	 * @Description 获取单个富文本信息
	 * @param htmlArea
	 * @return
	 * @author bchen
	 * @created 2014-6-27 下午10:23:36
	 */
	HtmlAreaVO findHtmlArea(HtmlAreaVO htmlArea);

	/**
	 * @Description 更新单个富文本信息
	 * @param htmlArea
	 * @return
	 * @author bchen
	 * @created 2014-6-28 下午7:17:18
	 */
	int updateHtmlArea(HtmlAreaVO htmlArea);

	/**
	 * @Description 删除富文本信息
	 * @param htmlArea
	 * @return
	 * @author bchen
	 * @created 2016-3-24 下午8:43:40
	 */
	int deleteHtmlArea(HtmlAreaVO htmlArea);
	
}
