package org.btsoft.jf.config.excel.dao;

import java.util.List;

import org.btsoft.jf.config.excel.entity.ExcelFieldVO;
import org.btsoft.jf.config.excel.entity.ExcelSheetVO;

/**
 * @ClassName IExcelFieldDao
 * @Description Excel字段Dao接口
 * @author bchen
 * @created 2016-5-23 下午10:29:25
 */
public interface IExcelFieldDao {

	/**
	 * @Description 获取某个sheet的字段配置
	 * @param excelSheet
	 * @return
	 * @author bchen
	 * @created 2016-5-23 下午10:30:20
	 */
	public List<ExcelFieldVO> findExcelFiledList(ExcelSheetVO excelSheet);
	
	/**
	 * @Description 创建excel字段
	 * @param excelField
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	public int createExcelField(ExcelFieldVO excelField);
	
	/**
	 * @Description 获取excel字段配置
	 * @param excelField
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:51:32
	 */
	public ExcelFieldVO findExcelField(ExcelFieldVO excelField);
	
	/**
	 * @Description 更新excel字段
	 * @param excelField
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	public int updateExcelField(ExcelFieldVO excelField);
	
	/**
	 * @Description 删除excel字段
	 * @param excelField
	 * @return
	 * @author bchen
	 * @created 2017-6-12 下午9:52:10
	 */
	public int deleteExcelField(ExcelFieldVO excelField);
}
