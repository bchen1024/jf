package org.btsoft.jf.config.i18n.dao;

import java.util.List;
import java.util.Map;

import org.btsoft.jf.config.i18n.entity.I18nVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;

/**
 * @ClassName II18nDao
 * @Description 国际化Dao接口
 * @author bchen
 * @created 2013-11-19 11:37:53
 */
public interface II18nDao {

	/**
	 * @Description 分页查询国际化信息
	 * @param i18n
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2013-12-24 10:14:36
	 */
	PagedResult<I18nVO> findI18nList(I18nVO i18n, PageVO page);

	/**
	 * @Description 获取单个国际化信息
	 * @param i18n
	 * @return
	 * @author bchen
	 * @created 2017年8月4日 下午11:45:15
	 */
	I18nVO findI18n(I18nVO i18n);

	/**
	 * @Description 插入国际化信息
	 * @param i18ns
	 * @author bchen
	 * @created 2013-12-30 9:53:54
	 */
	void createI18n(I18nVO i18n);

	/**
	 * @Description 更新国际化信息
	 * @param i18ns
	 * @author bchen
	 * @created 2013-12-31 12:45:57
	 */
	void updateI18n(I18nVO i18n);

	/**
	 * @Description 删除国际化信息
	 * @param i18ns
	 * @author bchen
	 * @created 2013-12-31 12:47:08
	 */
	void deleteI18n(I18nVO i18n);

	/**
	 * @Description 通过语言获取国际化信息缓存
	 * @param language
	 * @return
	 * @author bchen
	 * @created 2014-4-10 11:24:52
	 */
	List<I18nVO> findCacheI18ns(I18nVO i18n);

	/**
	 * @Description 根据国际化key值获取国际化信息
	 * @param i18nKey
	 * @return
	 * @author bchen
	 * @created 2017-6-10 上午11:38:28
	 */
	I18nVO findI18nByKey(String i18nKey);
	
	/**
	 * @Description 获取所有的国际化配置数据
	 * @param i18n
	 * @return
	 * @author bchen
	 * @created 2017年9月24日 上午12:45:18
	 */
	public List<Map<String,String>> findI18ns(I18nVO i18n);

}
