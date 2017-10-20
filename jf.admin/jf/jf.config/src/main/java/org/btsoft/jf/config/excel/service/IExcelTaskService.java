package org.btsoft.jf.config.excel.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.config.excel.entity.ExcelTaskConstant;
import org.btsoft.jf.config.excel.entity.ExcelTaskVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName IExcelTaskService
 * @Description Excel任务Service接口
 * @author bchen
 * @created 2016-5-21 下午10:17:35
 */
@Path("/excel/task")
@Produces(MediaType.APPLICATION_JSON)
public interface IExcelTaskService {

	/**
	 * @Description 分页获取某个当前用户的excel导入任务数据
	 * @param task
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-5-21 下午10:16:37
	 */
	@GET
	@Path(ExcelTaskConstant.MY_IMPORT_URL)
	public PagedResult<ExcelTaskVO> findImportTaskListByUser(
			@QueryParam("") ExcelTaskVO task, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 分页获取所有的excel导入任务数据
	 * @param task
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-5-21 下午10:16:37
	 */
	@GET
	@Path(ExcelTaskConstant.LIST_IMPORT_URL)
	public PagedResult<ExcelTaskVO> findImportTaskList(
			@QueryParam("") ExcelTaskVO task, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 分页获取某个当前用户的excel导出任务数据
	 * @param task
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-5-21 下午10:16:37
	 */
	@GET
	@Path(ExcelTaskConstant.MY_EXPORT_URL)
	public PagedResult<ExcelTaskVO> findExportTaskListByUser(
			@QueryParam("") ExcelTaskVO task, @QueryParam("") PageVO page)
			throws ApplicationException;

	/**
	 * @Description 分页获取所有的excel导出任务数据
	 * @param task
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-5-21 下午10:16:37
	 */
	@GET
	@Path(ExcelTaskConstant.LIST_EXPORT_URL)
	public PagedResult<ExcelTaskVO> findExportTaskList(
			@QueryParam("") ExcelTaskVO task, @QueryParam("") PageVO page)
			throws ApplicationException;
}
