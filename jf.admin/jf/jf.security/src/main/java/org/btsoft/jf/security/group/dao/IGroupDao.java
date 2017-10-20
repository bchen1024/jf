package org.btsoft.jf.security.group.dao;

import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.security.group.entity.GroupVO;

/**
 * @ClassName IGroupDao
 * @Description 群组Dao接口
 * @author bchen
 * @created 2013-11-19 11:21:26
 */
public interface IGroupDao {

	/**
	 * @Description 分页查询群组信息
	 * @param group
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2013-12-10 12:49:41
	 */
	PagedResult<GroupVO> findGroupList(GroupVO group, PageVO page);

	/**
	 * @Description 批量插入群组信息
	 * @param groups
	 * @author bchen
	 * @created 2013-12-10 12:40:33
	 */
	void createGroup(GroupVO group);

	/**
	 * @Description 批量更新群组信息
	 * @param groups
	 * @author bchen
	 * @created 2013-12-10 12:41:03
	 */
	void updateGroup(GroupVO group);

	/**
	 * @Description 批量删除群组信息
	 * @param groups
	 * @author bchen
	 * @created 2014-4-16 12:45:43
	 */
	void deleteGroup(GroupVO group);

	/**
	 * @Description 查询单个群组的信息
	 * @param group
	 * @return
	 * @author bchen
	 * @created 2015-9-6 下午11:21:09
	 */
	GroupVO findGroup(GroupVO group);
}
