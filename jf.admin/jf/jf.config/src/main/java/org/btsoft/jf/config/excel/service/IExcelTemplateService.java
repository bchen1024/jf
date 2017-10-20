package org.btsoft.jf.config.excel.service;

import java.util.List;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.btsoft.jf.config.excel.entity.ExcelTemplateConstant;
import org.btsoft.jf.config.excel.entity.ExcelTemplateVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;
import org.btsoft.jf.core.exception.ApplicationException;

/**
 * @ClassName IExcelTemplateService
 * @Description Excel模板Service接口
 * @author bchen
 * @created 2016-5-25 下午8:37:26
 */
@Path("/excel/template")
@Produces(MediaType.APPLICATION_JSON)
public interface IExcelTemplateService {

	/**
	 * @Description 分页获取excel导入模板
	 * @param excelTemplate
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-5-25 下午8:40:52
	 */
	@GET
	@Path(ExcelTemplateConstant.LIST_IMPORT_PATH)
	public PagedResult<ExcelTemplateVO> findImportExcelTemplateList(
			@QueryParam("") ExcelTemplateVO excelTemplate,
			@QueryParam("") PageVO page) throws ApplicationException;

	/**
	 * @Description 分页获取excel导出模板
	 * @param excelTemplate
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-5-25 下午8:40:52
	 */
	@GET
	@Path(ExcelTemplateConstant.LIST_EXPORT_PATH)
	public PagedResult<ExcelTemplateVO> findExportExcelTemplateList(
			@QueryParam("") ExcelTemplateVO excelTemplate,
			@QueryParam("") PageVO page) throws ApplicationException;

	/**
	 * @Description 创建excel导入模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	@POST
	@Path(ExcelTemplateConstant.CREATE_IMPORT_PATH)
	public void createExcelImportTemplate(ExcelTemplateVO excelTemplate)
			throws ApplicationException;

	/**
	 * @Description 创建excel导出模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	@POST
	@Path(ExcelTemplateConstant.CREATE_EXPORT_PATH)
	public void createExcelExportTemplate(ExcelTemplateVO excelTemplate)
			throws ApplicationException;

	/**
	 * @Description 更新excel导入模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	@PUT
	@Path(ExcelTemplateConstant.UPDATE_IMPORT_PATH)
	public void updateExcelImportTemplate(ExcelTemplateVO excelTemplate)
			throws ApplicationException;

	/**
	 * @Description 更新excel导出模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	@PUT
	@Path(ExcelTemplateConstant.UPDATE_EXPORT_PATH)
	public void updateExcelExportTemplate(ExcelTemplateVO excelTemplate)
			throws ApplicationException;

	/**
	 * @Description 更新excel导入模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	@DELETE
	@Path(ExcelTemplateConstant.DELETE_IMPORT_PATH)
	public void deleteExcelImportTemplate(ExcelTemplateVO excelTemplate)
			throws ApplicationException;

	/**
	 * @Description 更新excel导出模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	@DELETE
	@Path(ExcelTemplateConstant.DELETE_EXPORT_PATH)
	public void deleteExcelExportTemplate(ExcelTemplateVO excelTemplate)
			throws ApplicationException;

	/**
	 * @Description 获取单个excel导入模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	@GET
	@Path(ExcelTemplateConstant.SINGLE_IMPORT_PATH)
	public ExcelTemplateVO findExcelImportTemplate(
			@QueryParam("") ExcelTemplateVO excelTemplate)
			throws ApplicationException;

	/**
	 * @Description 获取单个excel导出模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	@GET
	@Path(ExcelTemplateConstant.SINGLE_EXPORT_PATH)
	public ExcelTemplateVO findExcelExportTemplate(
			@QueryParam("") ExcelTemplateVO excelTemplate)
			throws ApplicationException;

	/**
	 * @Description 获取某种类型模板编号
	 * @param excelTempate
	 * @return
	 * @throws ApplicationException
	 * @author bchen
	 * @created 2016-6-28 下午10:49:14
	 */
	@POST
	@Path(ExcelTemplateConstant.TEMPATE_TYPE_PATH)
	public List<Map<String, Object>> findExcelTemplateCodes(
			@PathParam("") ExcelTemplateVO excelTempate)
			throws ApplicationException;
}
