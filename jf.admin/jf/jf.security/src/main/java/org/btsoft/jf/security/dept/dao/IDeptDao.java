package org.btsoft.jf.security.dept.dao;

import java.util.List;

import org.btsoft.jf.security.dept.entity.DeptVO;

/**
 * @ClassName IDeptDao
 * @Description 组织机构Dao接口
 * @author bchen
 * @created 2016-3-28 下午7:07:22
 */
public interface IDeptDao {

	/**
	 * @Description 获取组织机构列表数据
	 * @param dept
	 * @return
	 * @author bchen
	 * @created 2016-3-28 下午7:08:18
	 */
	public List<DeptVO> findDeptList(DeptVO dept);
	
	/**
	 * @Description 创建组织机构
	 * @param dept
	 * @author bchen
	 * @created 2016-3-28 下午7:08:50
	 */
	public void createDept(DeptVO dept);
	
	
	/**
	 * @Description 获取单个组织机构信息
	 * @param dept
	 * @return
	 * @author bchen
	 * @created 2016-3-28 下午7:09:49
	 */
	public DeptVO findDept(DeptVO dept);
	
	/**
	 * @Description 更新组织机构
	 * @param dept
	 * @author bchen
	 * @created 2016-3-28 下午7:09:21
	 */
	public void updateDept(DeptVO dept);
	
	/**
	 * @Description 删除组织机构
	 * @param dept
	 * @author bchen
	 * @created 2016-3-28 下午7:10:26
	 */
	public void deleteDept(DeptVO dept);
}
