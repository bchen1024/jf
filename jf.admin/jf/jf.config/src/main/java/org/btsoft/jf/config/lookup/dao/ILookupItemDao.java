package org.btsoft.jf.config.lookup.dao;

import java.util.List;

import org.btsoft.jf.config.lookup.entity.LookupItemVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName ILookupDao
 * @Description Lookup dao接口
 * @author bchen
 * @created 2017年8月11日 下午7:27:50
 */
public interface ILookupItemDao {

	/**
	 * @Description 分页获取LookupItem配置
	 * @param lookupItem
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:32:52
	 */
	PagedResult<LookupItemVO> findLookupItemList(LookupItemVO lookupItem, PageVO page);

	/**
	 * @Description 获取单个lookupItem
	 * @param lookupItem
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:00
	 */
	LookupItemVO findLookupItem(LookupItemVO lookupItem);

	/**
	 * @Description 创建lookupItem
	 * @param lookupItem
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:11
	 */
	int createLookupItem(LookupItemVO lookupItem);

	/**
	 * @Description 更新LookupItem
	 * @param lookupItem
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:26
	 */
	int updateLookupItem(LookupItemVO lookupItem);

	/**
	 * @Description 删除LookupItem
	 * @param lookupItem
	 * @return
	 * @author bchen
	 * @created 2017年8月11日 下午9:35:38
	 */
	int deleteLookupItem(LookupItemVO lookupItem);

	/**
	 * @Description 根据代码编码获取项目列表
	 * @param lookupItem
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2017年9月6日 下午10:59:39
	 */
	List<LookupItemVO> findLookupItemByCodeNo(LookupItemVO lookupItem);
}
