package org.btsoft.jf.config.property.dao;

import java.util.List;

import org.btsoft.jf.config.property.entity.PropertyVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;

/**
 * @ClassName IPropertyDao
 * @Description 数据字典Dao接口
 * @author bchen
 * @created 2013-11-19 11:57:14
 */
public interface IPropertyDao {

	/**
	 * @Description 分页查询数据字典信息
	 * @param property  查询条件VO
	 * @param page 分页对象
	 * @return
	 * @author bchen
	 * @created 2013-12-24 10:33:52
	 */
	PagedResult<PropertyVO> findPropertyList(PropertyVO property, PageVO page);

	/**
	 * @Description 创建数据字典
	 * @param property
	 * @return
	 * @author bchen
	 * @created 2013-12-2 10:49:08
	 */
	int createProperty(PropertyVO property);

	/**
	 * @Description 查找单个数据字典
	 * @param property
	 * @return
	 * @author bchen
	 * @created 2013-12-2 10:46:52
	 */
	PropertyVO findProperty(PropertyVO property);

	/**
	 * @Description 更新单个数据字典
	 * @param property
	 * @return
	 * @author bchen
	 * @created 2013-12-2 10:49:57
	 */
	int updateProperty(PropertyVO property);

	/**
	 * @Description 更新字典特定字段值
	 * @param property
	 * @return
	 * @author bchen
	 * @created 2017年7月22日 下午5:39:53
	 */
	int updateSingleProperty(PropertyVO property);

	/**
	 * @Description 删除单个数据字典
	 * @param property
	 * @return
	 * @author bchen
	 * @created 2013-12-2 10:50:32
	 */
	int deleteProperty(PropertyVO property);
	
	/**
	 * @Description 获取数据字典列表
	 * @param property
	 * @return
	 * @author bchen
	 * @created 2017年9月25日 上午12:13:03
	 */
	List<PropertyVO> findPropertys(PropertyVO property);

}
