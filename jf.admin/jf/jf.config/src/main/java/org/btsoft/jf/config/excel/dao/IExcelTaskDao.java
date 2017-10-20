package org.btsoft.jf.config.excel.dao;

import org.btsoft.jf.config.excel.entity.ExcelTaskVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;

/**
 * @ClassName IExcelTaskDao
 * @Description Excel任务Dao接口
 * @author bchen
 * @created 2016-5-21 下午10:14:01
 */
public interface IExcelTaskDao {

	/**
	 * @Description 分页获取excel任务数据
	 * @param task
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-5-21 下午10:16:37
	 */
	public PagedResult<ExcelTaskVO> findExcelTaskList(ExcelTaskVO task,
			PageVO page);

	/**
	 * @Description 创建excel任务
	 * @param task
	 * @author bchen
	 * @created 2016-5-22 下午1:07:43
	 */
	public int createExcelTask(ExcelTaskVO task);

	/**
	 * @Description 更新excel任务
	 * @param task
	 * @author bchen
	 * @created 2016-5-22 下午1:08:45
	 */
	public void updateExcelTask(ExcelTaskVO task);
	
	/**
	 * @Description 获取某个excel任务的信息
	 * @param excelTask
	 * @return
	 * @author bchen
	 * @created 2016-5-27 下午8:44:07
	 */
	public ExcelTaskVO findExcelTask(ExcelTaskVO excelTask);
}
