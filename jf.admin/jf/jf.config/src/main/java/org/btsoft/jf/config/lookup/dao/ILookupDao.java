package org.btsoft.jf.config.lookup.dao;

import org.btsoft.jf.config.lookup.entity.LookupVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;

/**
 * @ClassName ILookupDao
 * @Description Lookup dao接口
 * @author bchen
 * @created 2017年8月11日 下午7:27:50
 */
public interface ILookupDao {

	/**
	 * @Description 分页获取Lookup配置
	 * @param lookup
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:32:52
	 */
	PagedResult<LookupVO> findLookupList(LookupVO lookup, PageVO page);

	/**
	 * @Description 获取单个Lookup
	 * @param lookup
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:00
	 */
	LookupVO findLookup(LookupVO lookup);

	/**
	 * @Description 创建Lookup
	 * @param lookup
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:11
	 */
	int createLookup(LookupVO lookup);

	/**
	 * @Description 更新Lookup
	 * @param lookup
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:26
	 */
	int updateLookup(LookupVO lookup);

	/**
	 * @Description 删除Lookup
	 * @param lookup
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:38
	 */
	int deleteLookup(LookupVO lookup);
}
