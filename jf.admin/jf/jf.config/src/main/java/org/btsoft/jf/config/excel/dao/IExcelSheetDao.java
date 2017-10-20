package org.btsoft.jf.config.excel.dao;

import java.util.List;

import org.btsoft.jf.config.excel.entity.ExcelSheetVO;

/**
 * @ClassName IExcelSheetDao
 * @Description Excel页签Dao接口
 * @author bchen
 * @created 2016-5-23 下午10:15:03
 */
public interface IExcelSheetDao {

	/**
	 * @Description 获取某个excel模板的sheet配置
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2016-5-23 下午10:20:44
	 */
	public List<ExcelSheetVO> findExcelSheetList(ExcelSheetVO excelSheet);
	
	/**
	 * @Description 创建excelSheet
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	public int createExcelSheet(ExcelSheetVO excelSheet);
	
	/**
	 * @Description 获取excel Sheet配置
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:51:32
	 */
	public ExcelSheetVO findExcelSheet(ExcelSheetVO excelSheet);
	
	/**
	 * @Description 更新excelSheet
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	public int updateExcelSheet(ExcelSheetVO excelSheet);
	
	/**
	 * @Description 删除excel Sheet
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	public int deleteExcelSheet(ExcelSheetVO excelSheet);
}
