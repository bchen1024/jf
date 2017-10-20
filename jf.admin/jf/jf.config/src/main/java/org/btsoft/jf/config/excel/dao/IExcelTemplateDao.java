package org.btsoft.jf.config.excel.dao;

import java.util.List;
import java.util.Map;

import org.btsoft.jf.config.excel.entity.ExcelTemplateVO;
import org.btsoft.jf.core.entity.PageVO;
import org.btsoft.jf.core.entity.PagedResult;

/**
 * @ClassName IExcelTemplateDao
 * @Description Excel模板Dao接口
 * @author bchen
 * @created 2016-5-22 下午8:24:48
 */
public interface IExcelTemplateDao {

	/**
	 * @Description 获取excel模板
	 * @param excelTemplate
	 * @return
	 * @author bchen
	 * @created 2016-5-23 下午9:57:50
	 */
	public ExcelTemplateVO findExcelTemplate(ExcelTemplateVO excelTemplate);

	/**
	 * @Description 分页获取excel模板
	 * @param excelTemplate
	 * @param page
	 * @return
	 * @author bchen
	 * @created 2016-5-25 下午9:26:51
	 */
	public PagedResult<ExcelTemplateVO> findExcelTemplateList(
			ExcelTemplateVO excelTemplate, PageVO page);

	/**
	 * @Description 创建excel模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	public void createExcelTemplate(ExcelTemplateVO excelTemplate);

	/**
	 * @Description 更新excel模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	public void updateExcelTemplate(ExcelTemplateVO excelTemplate);

	/**
	 * @Description 删除excel模板
	 * @param excelTemplate
	 * @author bchen
	 * @created 2016-5-26 上午12:10:14
	 */
	public void deleteExcelTemplate(ExcelTemplateVO excelTemplate);

	/**
	 * @Description 获取某种类型模板编号
	 * @param excelTempate
	 * @return
	 * @author bchen
	 * @created 2016-6-28 下午10:50:37
	 */
	public List<Map<String, Object>> findExcelTemplateCodes(
			ExcelTemplateVO excelTempate);

}
