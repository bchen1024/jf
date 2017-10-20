package org.btsoft.jf.config.excel.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.excel.dao.IExcelSheetDao;
import org.btsoft.jf.config.excel.entity.ExcelSheetConstant;
import org.btsoft.jf.config.excel.entity.ExcelSheetVO;
import org.btsoft.jf.config.excel.service.IExcelSheetService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;

/**
 * @ClassName ExcelSheetService
 * @Description 模板sheet服务实现类
 * @author bchen
 * @created 2016-8-5 下午10:58:55
 */
@Named
@JResource(code = ExcelSheetConstant.RESOURCE_CODE, descCN = ExcelSheetConstant.RESOURCE_DESC_CN, descEN = ExcelSheetConstant.RESOURCE_DESC_EN)
public class ExcelSheetService implements IExcelSheetService, IRestService {

	@Inject
	private IExcelSheetDao dao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = ExcelSheetConstant.LIST_DESC_CN, descEN = ExcelSheetConstant.LIST_DESC_EN)
	public List<ExcelSheetVO> findExcelSheetList(ExcelSheetVO excelSheet) {
		return dao.findExcelSheetList(excelSheet);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = ExcelSheetConstant.CREATE_DESC_CN, descEN = ExcelSheetConstant.CREATE_DESC_EN,isAudit=true)
	public void createExcelSheet(ExcelSheetVO excelSheet) {
		dao.createExcelSheet(excelSheet);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = ExcelSheetConstant.SINGLE_DESC_CN, descEN = ExcelSheetConstant.SINGLE_DESC_EN)
	public ExcelSheetVO findExcelSheet(ExcelSheetVO excelSheet) {
		return dao.findExcelSheet(excelSheet);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = ExcelSheetConstant.UPDATE_DESC_CN, descEN = ExcelSheetConstant.UPDATE_DESC_EN,isAudit=true)
	public void updateExcelSheet(ExcelSheetVO excelSheet) {
		dao.updateExcelSheet(excelSheet);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = ExcelSheetConstant.DELETE_DESC_CN, descEN = ExcelSheetConstant.DELETE_DESC_EN,isAudit=true)
	public void deleteExcelSheet(ExcelSheetVO excelSheet) {
		dao.deleteExcelSheet(excelSheet);
	}

}
