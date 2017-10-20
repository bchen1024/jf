package org.btsoft.jf.config.excel.service.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.excel.dao.IExcelTaskDao;
import org.btsoft.jf.config.excel.entity.ExcelTaskConstant;
import org.btsoft.jf.config.excel.entity.ExcelTaskVO;
import org.btsoft.jf.config.excel.service.IExcelTaskService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName ExcelTaskService
 * @Description Excel任务Service实现类
 * @author bchen
 * @created 2016-5-21 下午10:21:12
 */
@Named
@JResource(code = ExcelTaskConstant.RESOURCE_CODE, descCN = ExcelTaskConstant.RESOURCE_DESC_CN, descEN = ExcelTaskConstant.RESOURCE_DESC_EN)
public class ExcelTaskService implements IExcelTaskService,IRestService {

	@Inject
	private IExcelTaskDao excelTaskDao;

	@Override
	@JOperator(descCN = ExcelTaskConstant.MY_IMPORT_TASK_DESC_CN, descEN = ExcelTaskConstant.MY_IMPORT_TASK_DESC_EN)
	public PagedResult<ExcelTaskVO> findImportTaskListByUser(ExcelTaskVO task,
			PageVO page) throws ApplicationException {
		task.setTaskType(ExcelTaskConstant.TASK_TYPE_IMPORT);
		return excelTaskDao.findExcelTaskList(task, page);
	}

	@Override
	@JOperator(code = ExcelTaskConstant.LIST_IMPORT_TASK_CODE, descCN = ExcelTaskConstant.LIST_IMPORT_TASK_DESC_CN, descEN = ExcelTaskConstant.LIST_IMPORT_TASK_DESC_EN)
	public PagedResult<ExcelTaskVO> findImportTaskList(ExcelTaskVO task,
			PageVO page) throws ApplicationException {
		task.setDataOwner(ExcelTaskConstant.DATA_OWNER_ALL);
		task.setTaskType(ExcelTaskConstant.TASK_TYPE_IMPORT);
		return excelTaskDao.findExcelTaskList(task, page);
	}

	@Override
	@JOperator(descCN = ExcelTaskConstant.MY_EXPORT_TASK_DESC_CN, descEN = ExcelTaskConstant.MY_EXPORT_TASK_DESC_EN)
	public PagedResult<ExcelTaskVO> findExportTaskListByUser(ExcelTaskVO task,
			PageVO page) throws ApplicationException {
		task.setTaskType(ExcelTaskConstant.TASK_TYPE_EXPORT);
		return excelTaskDao.findExcelTaskList(task, page);
	}

	@Override
	@JOperator(code = ExcelTaskConstant.LIST_EXPORT_TASK_CODE, descCN = ExcelTaskConstant.LIST_EXPORT_TASK_DESC_CN, descEN = ExcelTaskConstant.LIST_EXPORT_TASK_DESC_EN)
	public PagedResult<ExcelTaskVO> findExportTaskList(ExcelTaskVO task,
			PageVO page) throws ApplicationException {
		task.setDataOwner(ExcelTaskConstant.DATA_OWNER_ALL);
		task.setTaskType(ExcelTaskConstant.TASK_TYPE_EXPORT);
		return excelTaskDao.findExcelTaskList(task, page);
	}

}
