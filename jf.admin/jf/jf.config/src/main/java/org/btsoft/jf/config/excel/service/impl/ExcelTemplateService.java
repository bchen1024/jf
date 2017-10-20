package org.btsoft.jf.config.excel.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import org.btsoft.jf.config.excel.dao.IExcelTemplateDao;
import org.btsoft.jf.config.excel.entity.ExcelTemplateConstant;
import org.btsoft.jf.config.excel.entity.ExcelTemplateVO;
import org.btsoft.jf.config.excel.service.IExcelTemplateService;
import org.btsoft.jf.core.annotation.JOperator;
import org.btsoft.jf.core.annotation.JResource;
import org.btsoft.jf.core.application.IRestService;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName ExcelTemplateService
 * @Description Excel模板Service实现类
 * @author bchen
 * @created 2016-5-25 下午8:42:08
 */
@Named
@JResource(code = ExcelTemplateConstant.RESOURCE_CODE, descCN = ExcelTemplateConstant.RESOURCE_DESC_CN, descEN = ExcelTemplateConstant.RESOURCE_DESC_EN)
public class ExcelTemplateService implements IExcelTemplateService, IRestService {

	@Inject
	private IExcelTemplateDao excelTemplateDao;

	@Override
	@JOperator(code = ExcelTemplateConstant.LIST_IMPORT_TEMPLATE_CODE, descCN = ExcelTemplateConstant.LIST_IMPORT_TEMPLATE_DESC_CN, descEN = ExcelTemplateConstant.LIST_IMPORT_TEMPLATE_DESC_EN)
	public PagedResult<ExcelTemplateVO> findImportExcelTemplateList(ExcelTemplateVO excelTemplate, PageVO page)
			throws ApplicationException {
		excelTemplate.setExcelTemplateType(ExcelTemplateConstant.TEMPLATE_TYPE_IMPORT);
		return excelTemplateDao.findExcelTemplateList(excelTemplate, page);
	}

	@Override
	@JOperator(code = ExcelTemplateConstant.LIST_EXPORT_TEMPLATE_CODE, descCN = ExcelTemplateConstant.LIST_EXPORT_TEMPLATE_DESC_CN, descEN = ExcelTemplateConstant.LIST_EXPORT_TEMPLATE_DESC_EN)
	public PagedResult<ExcelTemplateVO> findExportExcelTemplateList(ExcelTemplateVO excelTemplate, PageVO page)
			throws ApplicationException {
		excelTemplate.setExcelTemplateType(ExcelTemplateConstant.TEMPLATE_TYPE_EXPORT);
		return excelTemplateDao.findExcelTemplateList(excelTemplate, page);
	}

	@Override
	@JOperator(code = ExcelTemplateConstant.CREATE_IMPORT_CODE, descCN = ExcelTemplateConstant.CREATE_IMPORT_DESC_CN, descEN = ExcelTemplateConstant.CREATE_IMPORT_DESC_EN, isAudit = true)
	public void createExcelImportTemplate(ExcelTemplateVO excelTemplate) throws ApplicationException {
		excelTemplate.setExcelTemplateType(ExcelTemplateConstant.TEMPLATE_TYPE_IMPORT);
		excelTemplateDao.createExcelTemplate(excelTemplate);
	}

	@Override
	@JOperator(code = ExcelTemplateConstant.CREATE_EXPORT_CODE, descCN = ExcelTemplateConstant.CREATE_EXPORT_DESC_CN, descEN = ExcelTemplateConstant.CREATE_EXPORT_DESC_EN, isAudit = true)
	public void createExcelExportTemplate(ExcelTemplateVO excelTemplate) throws ApplicationException {
		excelTemplate.setExcelTemplateType(ExcelTemplateConstant.TEMPLATE_TYPE_EXPORT);
		excelTemplateDao.createExcelTemplate(excelTemplate);
	}

	@Override
	@JOperator(code = ExcelTemplateConstant.UPDATE_IMPORT_CODE, descCN = ExcelTemplateConstant.UPDATE_IMPORT_DESC_CN, descEN = ExcelTemplateConstant.UPDATE_IMPORT_DESC_EN, isAudit = true)
	public void updateExcelImportTemplate(ExcelTemplateVO excelTemplate) throws ApplicationException {
		excelTemplate.setExcelTemplateType(ExcelTemplateConstant.TEMPLATE_TYPE_IMPORT);
		excelTemplateDao.updateExcelTemplate(excelTemplate);
	}

	@Override
	@JOperator(code = ExcelTemplateConstant.UPDATE_EXPORT_CODE, descCN = ExcelTemplateConstant.UPDATE_EXPORT_DESC_CN, descEN = ExcelTemplateConstant.UPDATE_EXPORT_DESC_EN, isAudit = true)
	public void updateExcelExportTemplate(ExcelTemplateVO excelTemplate) throws ApplicationException {
		excelTemplate.setExcelTemplateType(ExcelTemplateConstant.TEMPLATE_TYPE_EXPORT);
		excelTemplateDao.updateExcelTemplate(excelTemplate);
	}

	@Override
	@JOperator(code = ExcelTemplateConstant.DELETE_IMPORT_CODE, descCN = ExcelTemplateConstant.DELETE_IMPORT_DESC_CN, descEN = ExcelTemplateConstant.DELETE_IMPORT_DESC_EN, isAudit = true)
	public void deleteExcelImportTemplate(ExcelTemplateVO excelTemplate) throws ApplicationException {
		excelTemplate.setExcelTemplateType(ExcelTemplateConstant.TEMPLATE_TYPE_IMPORT);
		excelTemplateDao.deleteExcelTemplate(excelTemplate);
	}

	@Override
	@JOperator(code = ExcelTemplateConstant.DELETE_EXPORT_CODE, descCN = ExcelTemplateConstant.DELETE_EXPORT_DESC_CN, descEN = ExcelTemplateConstant.DELETE_EXPORT_DESC_EN, isAudit = true)
	public void deleteExcelExportTemplate(ExcelTemplateVO excelTemplate) throws ApplicationException {
		excelTemplate.setExcelTemplateType(ExcelTemplateConstant.TEMPLATE_TYPE_EXPORT);
		excelTemplateDao.deleteExcelTemplate(excelTemplate);
	}

	@Override
	@JOperator(code = ExcelTemplateConstant.SINGLE_IMPORT_CODE, descCN = ExcelTemplateConstant.SINGLE_IMPORT_DESC_CN, descEN = ExcelTemplateConstant.SINGLE_IMPORT_DESC_EN)
	public ExcelTemplateVO findExcelImportTemplate(ExcelTemplateVO excelTemplate) throws ApplicationException {
		return excelTemplateDao.findExcelTemplate(excelTemplate);
	}

	@Override
	@JOperator(code = ExcelTemplateConstant.SINGLE_EXPORT_CODE, descCN = ExcelTemplateConstant.SINGLE_EXPORT_DESC_CN, descEN = ExcelTemplateConstant.SINGLE_EXPORT_DESC_EN)
	public ExcelTemplateVO findExcelExportTemplate(ExcelTemplateVO excelTemplate) throws ApplicationException {
		return excelTemplateDao.findExcelTemplate(excelTemplate);
	}

	@Override
	@JOperator(descCN = ExcelTemplateConstant.TEMPLATE_CODE_DESC_CN, descEN = ExcelTemplateConstant.TEMPLATE_CODE_DESC_EN)
	public List<Map<String, Object>> findExcelTemplateCodes(ExcelTemplateVO excelTempate) throws ApplicationException {
		return excelTemplateDao.findExcelTemplateCodes(excelTempate);
	}

}
