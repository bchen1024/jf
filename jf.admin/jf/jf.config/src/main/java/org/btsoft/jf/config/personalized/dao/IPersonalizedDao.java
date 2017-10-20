package org.btsoft.jf.config.personalized.dao;

import org.btsoft.jf.config.personalized.entity.PersonalizedVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;

/**
 * @ClassName IPersonalizedDao
 * @Description 个性化设置Dao接口
 * @author bchen
 * @created 2017年8月5日 下午12:52:30
 */
public interface IPersonalizedDao {

	/**
	 * @Description 保存个性化设置
	 * @param personalized
	 * @author bchen
	 * @created 2017年9月27日 上午12:50:35
	 */
	int savePersonalized(PersonalizedVO personalized);
	
	/**
	 * @Description 删除个性化设置
	 * @param personalized
	 * @return
	 * @author bchen
	 * @created 2017年9月27日 上午12:53:51
	 */
	int deletePersonalized(PersonalizedVO personalized);

	/**
	 * @Description 分页获取个性化设置列表
	 * @param personalized
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2017年8月5日 下午12:53:27
	 */
	PagedResult<PersonalizedVO> findPersonalizedList(PersonalizedVO personalized, PageVO page);

	/**
	 * @Description 获取单个个性化配置
	 * @param personalized
	 * @return
	 * @author bchen
	 * @created 2017年9月24日 下午11:35:45
	 */
	PersonalizedVO findPersonalized(PersonalizedVO personalized);
}
