package org.btsoft.jf.config.excel.service.impl;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.excel.dao.IExcelFieldDao;
import org.btsoft.jf.config.excel.entity.ExcelFieldConstant;
import org.btsoft.jf.config.excel.entity.ExcelFieldVO;
import org.btsoft.jf.config.excel.entity.ExcelSheetVO;
import org.btsoft.jf.config.excel.service.IExcelFieldService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.constants.CoreConstant;

/**
 * @ClassName ExcelFieldService
 * @Description Excel字段Service实现类
 * @author bchen
 * @created 2017-6-11 下午11:21:56
 */
@Named
@JResource(code = ExcelFieldConstant.RESOURCE_CODE, descCN = ExcelFieldConstant.RESOURCE_DESC_CN, descEN = ExcelFieldConstant.RESOURCE_DESC_EN)
public class ExcelFieldService implements IExcelFieldService, IRestService {

	@Inject
	private IExcelFieldDao dao;

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_LIST, descCN = ExcelFieldConstant.LIST_DESC_CN, descEN = ExcelFieldConstant.LIST_DESC_EN)
	public List<ExcelFieldVO> findExcelFiledList(ExcelSheetVO excelSheet) {
		return dao.findExcelFiledList(excelSheet);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_CREATE, descCN = ExcelFieldConstant.CREATE_DESC_CN, descEN = ExcelFieldConstant.CREATE_DESC_EN, isAudit = true)
	public void createExcelField(ExcelFieldVO excelField) {
		dao.createExcelField(excelField);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_SINGLE, descCN = ExcelFieldConstant.SINGLE_DESC_CN, descEN = ExcelFieldConstant.SINGLE_DESC_EN)
	public ExcelFieldVO findExcelField(ExcelFieldVO excelField) {
		return dao.findExcelField(excelField);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_UPDATE, descCN = ExcelFieldConstant.UPDATE_DESC_CN, descEN = ExcelFieldConstant.UPDATE_DESC_EN, isAudit = true)
	public void updateExcelField(ExcelFieldVO excelField) {
		dao.updateExcelField(excelField);
	}

	@Override
	@JOperator(code = CoreConstant.JOPERATOR_CODE_DELETE, descCN = ExcelFieldConstant.DELETE_DESC_CN, descEN = ExcelFieldConstant.DELETE_DESC_EN, isAudit = true)
	public void deleteExcelField(ExcelFieldVO excelField) {
		dao.deleteExcelField(excelField);
	}

}
